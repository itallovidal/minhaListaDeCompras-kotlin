package com.example.listadecompras.viewmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listadecompras.domain.DTOs.Product
import android.util.Log
import com.example.listadecompras.domain.DTOs.ProductList
import com.example.listadecompras.utility.Ktor
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.contentType
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.UUID


class ProductsViewModel(ktor: Ktor): ViewModel() {

    private val ktorClient = ktor.getClient()

    private val _products = MutableLiveData<List<Product>>(emptyList())
    val products: LiveData<List<Product>> = _products

    private val _total = MutableLiveData(getTotalValue())
    val total: LiveData<Double> = _total

    suspend fun sendListToApi(phoneID: UUID) = coroutineScope {
        if(_products.value != null && _products.value!!.isEmpty()) {
            this.cancel()
        }

        launch {
            val newList = ProductList(_products.value, phoneID.toString())
            try{
                Log.e("myLog", newList.toString())
                val response = ktorClient.post("http://10.0.2.2:3333/list") {
                    contentType(io.ktor.http.ContentType.Application.Json)
                    setBody(newList)
                }

                _products.value = emptyList()
                _total.value = getTotalValue()
                Log.e("myLog", response.bodyAsText())
            }catch (Error: Exception){
                Log.e("myLog", Error.message.toString())
            }
        }
    }

    fun addItemToCart(item: String){
        if(item.length < 3) return

        val actualProductList = _products.value!!.toMutableList()
        var newProduct = Product(name = item, price = 0.0, quantity = 1, id = 1)

        if(actualProductList.isNotEmpty()){
            newProduct = Product(name = item, price = 0.0, quantity = 1, id =  actualProductList.last().id + 1)
        }

        actualProductList.add(newProduct)
        _products.value = actualProductList
    }

    fun removeItemFromCart(id: Int){
        val filteredProducts = _products.value!!.filter { product -> product.id != id  }
        _products.value = filteredProducts
        _total.value = getTotalValue()
    }

    private fun getTotalValue(): Double{
        if(_products.value?.isEmpty() == true ||_products.value == null ){
            return 0.00
        }

        return _products.value!!.fold(0.0) { acc, product ->
            acc + (product.price * product.quantity)
        }
    }

    fun changeItemPrice(id: Int, price: String){
        var newPrice = price
        Log.e("myLog", price)
        if(price.isEmpty()) return

        if(price.contains(",")){
            newPrice = price.replace(",", ".") + "0"
        }

        Log.e("myLog", newPrice)


        val updatedProducts = _products.value!!.map {
            if(it.id == id){
                Log.e("myLog", "Novo Preço:")
                return@map it.copy(price = newPrice.toDouble())
            }
            return@map it
        }

        _products.value = updatedProducts
        Log.e("myLog", _products.value.toString())
        _total.value = getTotalValue()
    }

    fun changeItemQuantity(id: Int, quantity: String){
        Log.e("myLog", quantity)

        if(quantity.isEmpty()) return

        val newQuantity = quantity.toInt()
        val updatedProducts = _products.value!!.map { product ->
            if(product.id == id) {
                return@map product.copy(quantity = newQuantity)
            }

            return@map product
        }

        _products.value = updatedProducts

        _total.value = getTotalValue()
    }

    fun importNewList(inputText: String){
        if (inputText.length < 3) return

        val splittedText = inputText.split('\n').filter { it.isNotEmpty() }
        val filteredList = splittedText.map { it.trimEnd() }.map { it.trimStart() }

        var quantity = 0
        Log.e("myLog", "->")
        Log.e("myLog", filteredList.toString())


        val regexPickStrings =  Regex("[a-zA-Z ]")
        val regexPickAllNumbers = Regex("\\d")

        val newList = filteredList.mapIndexed { index, it ->
            val strings = regexPickStrings.findAll(it)
                .map { it.value }
                .joinToString("").trimStart()

            Log.e("myLog", regexPickAllNumbers.containsMatchIn(it).toString() )

            if(regexPickAllNumbers.containsMatchIn(it)){
                quantity = regexPickAllNumbers.findAll(it)
                    .map { it.value }
                    .joinToString("").toInt()
            }

            Log.e("myLog", "->")
            Log.e("myLog", strings)

            Log.e("myLog", "->")
            Log.e("myLog", quantity.toString())

            return@mapIndexed Product(
                strings,
                quantity = quantity ?: 0,
                id = index,
                price = 0.0
            )
        }


        Log.e("mylog", newList.toString())
        _products.value = newList
        _total.value = 0.0
    }
}

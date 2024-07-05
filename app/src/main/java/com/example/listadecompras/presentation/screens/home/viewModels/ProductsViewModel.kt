package com.example.listadecompras.presentation.screens.home.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listadecompras.domain.models.Product
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.example.listadecompras.MainApplication
import com.example.listadecompras.domain.models.ProductList
import com.example.listadecompras.domain.room.User
import com.example.listadecompras.viewmodels.AppDatabase
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpHeaders.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import java.util.UUID


class ProductsViewModel(): ViewModel() {

    private val httpClient: HttpClient = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    private val _products = MutableLiveData<List<Product>>(emptyList())
    val products: LiveData<List<Product>> = _products

    private val _total = MutableLiveData(getTotalValue())
    val total: LiveData<Double> = _total



    suspend fun getAllHistory() = coroutineScope{
        Log.e("log3", "clicado 2")
        launch {
//            val response = this@ProductsViewModel.httpClient.get("https://api.adviceslip.com/advice").bodyAsText()
            try{
                val response = this@ProductsViewModel.httpClient.get("http://10.0.2.2:3333/list/26f4587e-4ed4-42f9-9146-90397ecc8fc1").bodyAsText()
                Log.e("log3", response)
            }catch (Error: Exception){
                Log.e("log3", Error.message.toString())
            }
        }
    }

    suspend fun sendListToApi(phoneID: UUID) = coroutineScope {
        if(_products.value != null && _products.value!!.isEmpty()) {
            this.cancel()
        }

        launch {
            val newList = ProductList(_products.value, phoneID.toString())
            try{
                Log.e("log3", newList.toString())
                val response = httpClient.post("http://10.0.2.2:3333/list") {
                    contentType(io.ktor.http.ContentType.Application.Json)
                    setBody(newList)
                }

                _products.value = emptyList()
                _total.value = getTotalValue()
                Log.e("log3", response.bodyAsText())
            }catch (Error: Exception){
                Log.e("log3", Error.message.toString())
            }


        }
    }


    fun addItemToCart(item: String){
        if(item.length < 3) return
        val actualProductList = _products.value?.toMutableList()
        var newProduct = Product(name = item, price = 0.0, quantity = 1, id = 1)
        if(actualProductList !== null && actualProductList.isNotEmpty()){
            newProduct = Product(name = item, price = 0.0, quantity = 1, id =  actualProductList.last().id + 1)
        }
        actualProductList?.add(newProduct)
        _products.value = actualProductList
    }

    fun removeItemFromCart(id: Int){
        val filteredProducts = _products.value?.filter { product -> product.id != id  }
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
        Log.e("mylog", price)

        if(price.isEmpty()) return

        val newPrice = price.toDouble()

        val updatedProducts = _products.value?.map {
            if(it.id == id){
                Log.e("mylog", "Novo Preço:")
                Log.e("mylog", newPrice.toString())
                return@map it.copy(price = newPrice)
            }

            return@map it
        }
        _products.value = updatedProducts

        _total.value = getTotalValue()
    }

    fun changeItemQuantity(id: Int, quantity: String){
        Log.e("mylog", quantity)

        if(quantity.isEmpty()) return

        val newQuantity = quantity.toInt()
        val updatedProducts = _products.value?.map { product ->
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
        Log.e("log2", "->")
        Log.e("log2", filteredList.toString())


        val regexPickStrings =  Regex("[a-zA-Z ]")
        val regexPickAllNumbers = Regex("\\d")

        val newList = filteredList.mapIndexed { index, it ->
            val strings = regexPickStrings.findAll(it)
                .map { it.value }
                .joinToString("").trimStart()

            Log.e("log2", regexPickAllNumbers.containsMatchIn(it).toString() )

            if(regexPickAllNumbers.containsMatchIn(it)){
                quantity = regexPickAllNumbers.findAll(it)
                    .map { it.value }
                    .joinToString("").toInt()
            }

            Log.e("log2", "->")
            Log.e("log2", strings)

            Log.e("log2", "->")
            Log.e("log2", quantity.toString())

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







//    private val _counter = MutableLiveData(0)
//    val counter: LiveData<Int> = _counter
//
//    fun incrementCounter() {
//        _counter.value = (_counter.value ?: 0) + 1
//    }

//    val products = remember { mutableStateListOf<Product>() }
//    val totalValue by remember(products) {
//        derivedStateOf {
//            products.fold(0.0) { acc, product ->
//                acc + (product.price * product.quantity)
//            }
//        }
//    }
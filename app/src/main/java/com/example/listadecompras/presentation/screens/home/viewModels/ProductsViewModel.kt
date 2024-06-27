package com.example.listadecompras.presentation.screens.home.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listadecompras.domain.models.Product
import android.util.Log


class ProductsViewModel: ViewModel() {
    private val _products = MutableLiveData<List<Product>>(emptyList())
    val products: LiveData<List<Product>> = _products


    private val _total = MutableLiveData(getTotalValue())
    val total: LiveData<Double> = _total


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

    fun showCart(): List<Product>?{
        return products.value
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
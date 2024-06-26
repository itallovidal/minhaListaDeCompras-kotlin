package com.example.listadecompras.presentation.screens.home.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listadecompras.domain.models.Product

class ProductsViewModel: ViewModel() {
    private val _products = MutableLiveData<List<Product>>(emptyList())
    val products: LiveData<List<Product>> = _products

    fun addItemToCart(item: String){
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
    }

    fun getTotalValue(): Double{
        if(_products.value?.isEmpty() == true ||_products.value == null ){
            return 0.00
        }

        return _products.value!!.fold(0.0) { acc, product ->
            acc + (product.price * product.quantity)
        }
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
package com.example.listadecompras.presentation.screens.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.listadecompras.presentation.screens.home.viewModels.ProductsViewModel

@Composable
fun Cart(productsViewModel: ProductsViewModel, modifier: Modifier = Modifier){
    val products by productsViewModel.products.observeAsState(initial = emptyList())

    LazyColumn(
        modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(products, key = { product -> product.id }) { product ->
            CartItem(product, productsViewModel)
        }
    }
}
package com.example.listadecompras.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.listadecompras.presentation.screens.home.composables.Cart
import com.example.listadecompras.presentation.screens.home.composables.Header
import com.example.listadecompras.viewmodels.ProductsViewModel
import com.example.listadecompras.viewmodels.AppDatabase

@Composable
fun Home(productsViewModel: ProductsViewModel, appDatabase: AppDatabase){
    Column {
        Header(productsViewModel, appDatabase)
        Cart(productsViewModel)

    }
}
package com.example.listadecompras.presentation.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.listadecompras.presentation.ui.globalComposables.Logo
import com.example.listadecompras.presentation.ui.theme.neutralOne
import com.example.listadecompras.presentation.ui.theme.neutralThree
import com.example.listadecompras.utility.getGradient
import com.example.listadecompras.viewmodels.ProductsViewModel
import com.example.listadecompras.viewmodels.AppDatabase

@Composable
fun Header(productsViewModel: ProductsViewModel, appDatabase: AppDatabase){
    val total by productsViewModel.total.observeAsState(initial = 0.0)

    Column(
        modifier = Modifier
            .background(getGradient(neutralOne, neutralThree))
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ){
            Logo()
            FinishListButton(productsViewModel, appDatabase)
        }
        AddItemInput(productsViewModel)
        Resume(total)
    }
}
package com.example.listadecompras.presentation.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.listadecompras.Logo
import com.example.listadecompras.MainActivity
import com.example.listadecompras.presentation.screens.home.viewModels.ProductsViewModel

@Composable
fun Header(productsViewModel: ProductsViewModel, activity: MainActivity, modifier: Modifier = Modifier){
    val gradient = Brush.linearGradient(
        0.0f to Color(0xFF323238),
        500.0f to Color(0xFF121214),
        start = Offset.Zero,
        end = Offset.Infinite,
    )

    Column(
        modifier = Modifier
            .background(gradient)
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Logo()
        AddItemInput(productsViewModel, modifier)
        Resume(productsViewModel, modifier)
    }
}
package com.example.listadecompras.presentation.screens.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.listadecompras.presentation.screens.home.viewModels.ProductsViewModel

@Composable
fun Resume(
    productsViewModel: ProductsViewModel,
    modifier: Modifier = Modifier,
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween){
        Text("Valor total", color = Color.White)
        Text(productsViewModel.getTotalValue().toString(), color = Color.White)
    }
}
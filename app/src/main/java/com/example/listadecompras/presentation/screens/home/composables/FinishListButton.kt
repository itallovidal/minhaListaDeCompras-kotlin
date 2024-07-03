package com.example.listadecompras.presentation.screens.home.composables

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.listadecompras.presentation.screens.home.viewModels.ProductsViewModel
import kotlinx.coroutines.launch

@Composable
fun FinishListButton(productsViewModel: ProductsViewModel){
    val coroutineScope = rememberCoroutineScope()

    Button(
        modifier = Modifier
            .fillMaxWidth()
        ,
        shape = RoundedCornerShape(5.dp),
        onClick = {
        coroutineScope.launch {
            productsViewModel.sendListToApi()
        }

    }) {
        Text("Finalizar Lista")
    }
}
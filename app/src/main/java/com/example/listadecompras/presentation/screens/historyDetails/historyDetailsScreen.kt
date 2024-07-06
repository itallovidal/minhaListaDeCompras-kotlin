package com.example.listadecompras.presentation.screens.historyDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.listadecompras.presentation.Logo
import com.example.listadecompras.presentation.screens.history.HistoryViewModel
import com.example.listadecompras.presentation.screens.home.composables.CartItem

@Composable
fun HistoryDetailsScreen(historyViewModel: HistoryViewModel, listID: Int?){

    val list = historyViewModel.history.value?.find { list -> list.id == listID }




    val gradient = Brush.linearGradient(
        0.0f to Color(0xFF323238),
        500.0f to Color(0xFF121214),
        start = Offset.Zero,
        end = Offset.Infinite,
    )

    Column(
        modifier = Modifier
            .background(gradient)
            .padding(32.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Logo()
        LazyColumn(
            Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            if (list != null) {
                items(list.product, key = { product -> product.id }) { product ->
                    CartItem(product = product, null, isReadOnly = true )
                }
            }
        }
    }
}
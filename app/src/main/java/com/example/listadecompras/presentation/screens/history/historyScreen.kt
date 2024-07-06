package com.example.listadecompras.presentation.screens.history
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.listadecompras.presentation.ui.globalComposables.Logo
import com.example.listadecompras.presentation.screens.history.composables.HistoryItem
import com.example.listadecompras.viewmodels.AppDatabase
import kotlinx.coroutines.runBlocking

@Composable
fun HistoryScreen(historyViewModel: HistoryViewModel, navController: NavController, appDatabase: AppDatabase){
    val phoneID by appDatabase.phoneID.observeAsState()
    val gradient = Brush.linearGradient(
        0.0f to Color(0xFF323238),
        500.0f to Color(0xFF121214),
        start = Offset.Zero,
        end = Offset.Infinite,
    )

    val historyList by historyViewModel.history.observeAsState(initial = emptyList())

    if(phoneID != null){
         runBlocking {
            historyViewModel.getAllHistory(phoneID!!.uid)
        }
    }

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
            items(historyList, key = { history -> history.id }) { list ->
                HistoryItem(list.totalPrice, list.totalQuantity, list.id, navController)
            }
        }
    }
}
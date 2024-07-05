package com.example.listadecompras.presentation.screens.history.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.listadecompras.domain.models.Product
import com.example.listadecompras.presentation.screens.home.viewModels.ProductsViewModel
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun HistoryItem(totalPrice: Double, totalQuantity: Double, listID: Int, navController: NavController){
            Row(
                Modifier
                    .clip(shape = RoundedCornerShape(5.dp))
                    .background(Color(0xFF323238))
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("historyDetails/$listID")
                    },
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically)
            {
                Column(
                    Modifier.weight(1f)
                ){
                    Text(
                        text = "Preço: $totalPrice",
                        color = Color.White,
                    )
                    Text(
                        text = "Quantidade: $totalQuantity",
                        color = Color.White,
                    )
                }
                Icon(Icons.Filled.KeyboardArrowRight, contentDescription = null, tint = Color.White)
            }
    }
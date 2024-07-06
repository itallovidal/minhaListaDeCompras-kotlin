package com.example.listadecompras.presentation.screens.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.listadecompras.R
import com.example.listadecompras.viewmodels.ProductsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemInput(productsViewModel: ProductsViewModel){
    var item by remember { mutableStateOf(TextFieldValue("")) }

    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        TextField(
            value = item,
            onValueChange = {
                item = it
            },
            Modifier
                .weight(1.0f)
            ,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                textColor = Color.White
            ),
            placeholder = { Text("Digite o produto..") }
        )
        Button(
            onClick = { ->
                run {
                    productsViewModel.addItemToCart(item.text)
                    item = TextFieldValue("")
                }
            },
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
            )

        ) {
            Icon(
                imageVector = Icons.Default.Add,
                modifier = Modifier.size(50.dp),
                contentDescription = "drawable icons",
                tint = Color(0xFF379DF1)
            )
        }
    }
}
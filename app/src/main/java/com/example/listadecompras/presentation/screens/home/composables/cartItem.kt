package com.example.listadecompras.presentation.screens.home.composables

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.listadecompras.R
import com.example.listadecompras.domain.models.Product
import com.example.listadecompras.presentation.screens.home.viewModels.ProductsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartItem(product: Product, productsViewModel: ProductsViewModel, modifier: Modifier = Modifier){
    val trash = ImageVector.vectorResource(id = R.drawable.trashicon)
    var price by remember { mutableStateOf(TextFieldValue(product.price.toString())) }
    var quantity by remember { mutableStateOf(TextFieldValue(product.quantity.toString())) }
    val maxInputLength = 4


    Row(
        modifier
            .clip(shape = RoundedCornerShape(5.dp))
            .background(Color(0xFF323238))
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            modifier.weight(1f)
        ){
            Text(
                text = product.name,
                color = Color.White,
            )
        }
        TextField(
            value = price,
            onValueChange = {
                if (it.text.length <= maxInputLength) {
                    price = it
                    productsViewModel.changeItemPrice(product.id, it.text)
                }
            },

            modifier
                .weight(1.0f)
                .clip(shape = RoundedCornerShape(5.dp))
            ,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color(R.color.primary),
            )
        )
        TextField(
            value = quantity,
            onValueChange = {
                if (it.text.length <= maxInputLength) {
                    quantity = it
                    productsViewModel.changeItemQuantity(product.id, it.text)
                }
            },
            modifier
                .clip(shape = RoundedCornerShape(5.dp))
                .weight(1.0f),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color(R.color.primary),
            )
        )
        Button(
            onClick = { productsViewModel.removeItemFromCart(product.id) },
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        ) {
            Icon(
                imageVector = trash,
                contentDescription = "drawable icons",
                tint = Color.Unspecified
            )
        }
    }

}
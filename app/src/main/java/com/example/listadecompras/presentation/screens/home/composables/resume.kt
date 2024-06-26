package com.example.listadecompras.presentation.screens.home.composables

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun Resume(
    total: Double,
    modifier: Modifier = Modifier,
){
    Log.e("mylog", "valor total:")
    Log.e("mylog", total.toString())


    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween){
        Text("Valor total", color = Color.White)
        Text(total.toString(), color = Color.White)
    }
}
package com.example.listadecompras.presentation.ui.globalComposables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.listadecompras.R

@Composable
fun Logo(){
    val logo = painterResource(R.drawable.logolistadecompras)

    Image(
        modifier = Modifier
            .height(72.dp)
            .padding(12.dp),
        contentScale = ContentScale.FillHeight,
        painter = logo,
        contentDescription = null
    )
}
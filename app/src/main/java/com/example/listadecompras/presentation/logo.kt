package com.example.listadecompras.presentation

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
fun Logo(modifier: Modifier = Modifier){
    val logo = painterResource(R.drawable.logolistadecompras)

    Image(
        modifier = modifier
            .height(80.dp)
            .padding(12.dp),
        contentScale = ContentScale.FillHeight,
        painter = logo,
        contentDescription = null
    )
}
package com.example.listadecompras.presentation.screens.home.composables

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.listadecompras.presentation.screens.home.viewModels.ProductsViewModel
import com.example.listadecompras.viewmodels.AppDatabase
import kotlinx.coroutines.launch
import java.util.UUID

@Composable
fun FinishListButton(productsViewModel: ProductsViewModel, appDatabase: AppDatabase){
    val coroutineScope = rememberCoroutineScope()
    val phoneID by appDatabase.phoneID.observeAsState()

    Log.e("tag2", phoneID?.uid.toString())

    Button(
        modifier = Modifier
            .fillMaxWidth()
        ,
        shape = RoundedCornerShape(5.dp),
        onClick = {
        coroutineScope.launch {
            val uid: UUID
            if(phoneID != null){
                uid = phoneID!!.uid
            }else{
                val newUUID = UUID.randomUUID()
                appDatabase.createPhoneID(newUUID)
                uid = newUUID
            }

            productsViewModel.sendListToApi(uid)
        }

    }) {
        Text("Finalizar Lista")
    }
}
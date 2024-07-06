package com.example.listadecompras.presentation.screens.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listadecompras.domain.DTOs.SavedList
import com.example.listadecompras.domain.DTOs.HistoryResponse
import com.example.listadecompras.utilityClass.Ktor
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.util.UUID

class HistoryViewModel(ktor: Ktor): ViewModel(){
    private val ktorClient = ktor.getClient()

    private val _history = MutableLiveData<List<SavedList>>()
    val history: LiveData<List<SavedList>> = _history

    suspend fun getAllHistory(phoneID: UUID) = coroutineScope{
        launch {
            try{
                val response = ktorClient.get("http://10.0.2.2:3333/list/${phoneID}").body<HistoryResponse>()
                _history.value = response.history
            }catch (error: Exception){
                Log.e("myLog", error.message.toString())
            }
        }
    }
}
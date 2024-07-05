package com.example.listadecompras.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listadecompras.MainApplication
import com.example.listadecompras.domain.room.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class AppDatabase : ViewModel(){
    private val db = MainApplication.userDatabase.userDao()
    val phoneID : LiveData<User> = db.getUUID()

    fun createPhoneID(id: UUID){
        viewModelScope.launch(Dispatchers.IO) {
            db.insert(User(id))
        }
    }
}
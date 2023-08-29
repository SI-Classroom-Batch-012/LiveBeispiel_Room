package com.example.livedatademo2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livedatademo2.database.Repository
import com.example.livedatademo2.database.getDatabase
import com.example.livedatademo2.database.model.Guest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewmodel(app: Application) : AndroidViewModel(app) {

    val database = getDatabase(app)
    val repository = Repository(database)

    val allGuests: LiveData<List<Guest>> = repository.allGuests

    fun insertGuest(guest:Guest){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertGuest(guest)
        }


    }

}
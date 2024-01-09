package com.example.livedatademo2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.livedatademo2.database.Repository
import com.example.livedatademo2.database.local.getDatabase
import com.example.livedatademo2.database.model.Guest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Sechster Schritt

class MainViewmodel(app: Application) : AndroidViewModel(app) {

    val database = getDatabase(app)
    val repository = Repository(database)

    val allGuests: LiveData<List<Guest>> = repository.allGuests

    fun getGuest(guestId: Long): LiveData<Guest> = repository.getGuest(guestId)

    fun deleteGuest(guest: Guest) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteGuest(guest)
        }
    }

    fun insertGuest(guest: Guest) {
        // Fehler war hier zu finden -> mit AndroidViewmodel müssen wir einen Dispatcher angeben
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertGuest(guest)
        }
    }
}
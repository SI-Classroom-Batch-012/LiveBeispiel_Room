package com.example.livedatademo2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    //Dao -> fun getAllGuests() : LiveData<List<Guest>>
    val allGuests: LiveData<List<Guest>> = repository.allGuests

    //Diese Funktion liefert die LiveData aus der Datenbank für einen spezifischen Guest
    fun getGuest(guestId: Long): LiveData<Guest> = repository.getGuest(guestId)

    fun deleteGuest(guest: Guest) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteGuest(guest)

        }
    }

    fun insertGuest(guest: Guest) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertGuest(guest)

        }


    }

    //Dao -> suspend fun getAllGuestsNoLD() : List<Guest>
    private val _allGuests2: MutableLiveData<List<Guest>> = MutableLiveData()
    val allGuests2: LiveData<List<Guest>>
        get() = _allGuests2

//    fun loadGuests(){
//        viewModelScope.launch(Dispatchers.IO) {
//
//            var guestList = repository.loadGuests()
//            guestList = guestList.sortedBy { it.foodPreference }
//
//
//            _allGuests2.postValue(guestList)
//        }
//    }

}
package com.example.livedatademo2.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.livedatademo2.database.model.Guest
import java.lang.Exception

class Repository(val database: GuestDatabase) {

    val allGuests: LiveData<List<Guest>> = database.dao.getAllGuests()

    fun getGuest(guestId: Int): LiveData<Guest> = database.dao.getGuestById(guestId)
//    Längere Schreibweise:
//    fun getGuest(guestId: Int): LiveData<Guest> {
//        return database.dao.getGuestById(guestId)
//    }


    suspend fun insertGuest(guest: Guest){
        try {
            database.dao.insertGuest(guest)
        } catch (ex: Exception){
            Log.e("Repository", "$ex")
        }
    }

//    suspend fun loadGuests() : List<Guest> {
//
//        return database.dao.getAllGuestsNoLD()
//    }

}
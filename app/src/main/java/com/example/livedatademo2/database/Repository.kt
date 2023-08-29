package com.example.livedatademo2.database

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.livedatademo2.database.model.Guest
import java.lang.Exception

class Repository(val database: GuestDatabase) {

    val allGuests: LiveData<List<Guest>> = database.dao.getAllGuests()

    suspend fun insertGuest(guest: Guest){
        try {
            database.dao.insertGuest(guest)
        } catch (ex: Exception){
            Log.e("Repository", "$ex")
        }
    }

}
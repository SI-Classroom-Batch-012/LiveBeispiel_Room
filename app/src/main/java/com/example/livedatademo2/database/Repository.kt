package com.example.livedatademo2.database

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.livedatademo2.database.local.GuestDatabase
import com.example.livedatademo2.database.model.Guest
import java.lang.Exception

// Vierter Schritt

class Repository(val database: GuestDatabase) {

    val allGuests: LiveData<List<Guest>> = database.dao.getAllGuests()

    fun getGuest(guestId: Long): LiveData<Guest> = database.dao.getGuestById(guestId)

    fun deleteGuest(guest: Guest) = database.dao.deleteGuestById(guest.id)

    suspend fun insertGuest(guest: Guest){
        try {
            database.dao.insertGuest(guest)
        } catch (ex: Exception){
            Log.e("Repository", "$ex")
        }
    }
}
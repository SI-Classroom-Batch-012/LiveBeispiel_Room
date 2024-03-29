package com.example.livedatademo2.database.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.livedatademo2.database.model.Guest

// Dritter Schritt

@Dao
interface GuestDatabaseDao {

    //Livedata als return -> kein suspend
    @Query("SELECT * FROM Guest")
    fun getAllGuests() : LiveData<List<Guest>>

    @Query("SELECT * FROM Guest WHERE id=:guestId")
    fun getGuestById(guestId: Long) : LiveData<Guest>

    @Query("DELETE FROM Guest WHERE id=:guestId")
    fun deleteGuestById(guestId: Long)

    //Kein return -> kein suspend
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGuest(guest: Guest)
}
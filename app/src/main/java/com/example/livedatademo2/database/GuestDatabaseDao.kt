package com.example.livedatademo2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.livedatademo2.database.model.Guest

@Dao
interface GuestDatabaseDao {

    //Livedata als return -> kein suspend
    @Query("SELECT * FROM Guest")
    fun getAllGuests() : LiveData<List<Guest>>

    @Query("SELECT * FROM Guest WHERE id=:guestId")
    fun getGuestById(guestId: Int) : LiveData<Guest>

    //Kein return -> kein suspend
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGuest(guest: Guest)



    //return, aber kein LiveData -> Suspend
//    @Query("SELECT * FROM Guest")
//    suspend fun getAllGuestsNoLD() : List<Guest>


}
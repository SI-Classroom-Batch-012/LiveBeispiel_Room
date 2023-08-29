package com.example.livedatademo2.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.livedatademo2.database.model.Guest

@Database(entities = [Guest::class], version = 1)
abstract class GuestDatabase : RoomDatabase() {
    abstract val dao: GuestDatabaseDao
}

private lateinit var INSTANCE : GuestDatabase

fun getDatabase(context: Context): GuestDatabase{

    synchronized(GuestDatabase::class.java){
        if(!::INSTANCE.isInitialized){


            //Neue Datenbank Instanz erstellen
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                GuestDatabase::class.java,
                "guest_database"
            ).build()
        }

        return INSTANCE
    }

}

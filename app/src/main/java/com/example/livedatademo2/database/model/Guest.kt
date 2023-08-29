package com.example.livedatademo2.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Guest(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    var name: String,
    var foodPreference: String,
)
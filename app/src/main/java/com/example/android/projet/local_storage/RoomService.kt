package com.example.android.projet.local_storage

import android.content.Context
import androidx.room.Room

object RoomService {
    lateinit var context: Context
    val appDatabase: AppDatabase by lazy {
        Room.databaseBuilder(context,
            AppDatabase::class.java, "projet_mobile").allowMainThreadQueries().build()
    }
}
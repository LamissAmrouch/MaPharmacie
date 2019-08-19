package com.example.android.projet

import android.app.Application
import com.example.android.projet.local_storage.RoomService

class App: Application(){
    override fun onCreate() {
        super.onCreate()
        RoomService.context = applicationContext
    }
}
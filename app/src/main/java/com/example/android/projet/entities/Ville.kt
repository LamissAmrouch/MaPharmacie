package com.example.android.projet.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName ="ville" )
data class Ville (
    var nomV:String) {
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}

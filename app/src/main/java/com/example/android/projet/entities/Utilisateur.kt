package com.example.android.projet.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.math.BigInteger


@Entity(tableName ="utilisateur", indices = arrayOf(Index(value = ["numero_tel"], unique = true)) )
data class Utilisateur (

    var nom:String,
    var prenom:String,
    var adresse:String,
    var numero_tel:Int,
    var mot_de_passe:String,
    @PrimaryKey
    var NSS:Int?=null,
    var isSynchronized:Int =0
    )

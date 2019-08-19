package com.example.android.projet.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigInteger


@Entity(tableName ="utilisateur" )
data class Utilisateur (
    var nom:String,
    var prenom:String,
    var adresse:String,
    var numero_tel:Int,
    var mot_de_passe:String,
    var isSynchronized:Int =0,
    @PrimaryKey
    var NSS:Int?=null)

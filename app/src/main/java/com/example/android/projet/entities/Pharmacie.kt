package com.example.android.projet.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName ="pharmacie" ,
    foreignKeys = arrayOf(
        ForeignKey(entity = Ville::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("id_ville"),
            onDelete = ForeignKey.CASCADE)
    )
)
data class Pharmacie (
    var nom:String,
    var adresse:String,
    //var horaire_ouverture: Timestamp,
  //  var horaire_fermeture:Timestamp,
    var lien_fb:String,
    var lien_localisation:String,
    val id_ville: Int?,
    var caisse:String,
    var isSynchronized:Int =0
    ) {
    @PrimaryKey(autoGenerate = true)
    var idP:Int?=null
}

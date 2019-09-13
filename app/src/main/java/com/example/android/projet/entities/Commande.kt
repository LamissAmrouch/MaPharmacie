package com.example.android.projet.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Date


@Entity(tableName ="commande" ,
    foreignKeys = arrayOf(
        ForeignKey(entity = Utilisateur::class,
            parentColumns = arrayOf("NSS"),
            childColumns = arrayOf("id_utilisateur")),
        ForeignKey(entity = Pharmacie::class,
            parentColumns = arrayOf("idP"),
            childColumns = arrayOf("id_pharmacie"))
    )

)
data class Commande (
    var date_envoi:String,
    var photo_ordonnance:String,
    var etat_traitement:String="Reçu",
    val id_utilisateur: Int,
    val id_pharmacie: Int
    ){
    @PrimaryKey(autoGenerate = true)
    var idC:Int?=null
}

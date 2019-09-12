package com.example.android.projet.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Date


@Entity(tableName ="commande" ,
    foreignKeys = arrayOf(
        ForeignKey(entity = Utilisateur::class,
            parentColumns = arrayOf("NSS"),
            childColumns = arrayOf("id_utilisateur"),
            onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Pharmacie::class,
            parentColumns = arrayOf("idP"),
            childColumns = arrayOf("id_pharmacie"),
            onDelete = ForeignKey.CASCADE)
    )

)
data class Commande (
    var date_envoi:String,
    var photo_ordonnance:String,
    var etat_traitement:String,
    val id_pharmacie: Int,
    val id_utilisateur: Int,
    var isSynchronized:Int =0
    ) {
    @PrimaryKey(autoGenerate = true)
    var idC:Int?=null
}

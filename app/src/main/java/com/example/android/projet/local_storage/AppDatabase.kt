package com.example.android.projet.local_storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android.projet.entities.Commande
import com.example.android.projet.entities.Pharmacie
import com.example.android.projet.entities.Utilisateur
import com.example.android.projet.entities.Ville

@Database(entities = arrayOf(
    Commande::class,
    Pharmacie::class,
    Utilisateur::class,
    Ville::class),version = 1)
@TypeConverters(DateConverter::class,TimestampConverter::class)

abstract class AppDatabase: RoomDatabase() {
    abstract fun getCommandeDAO():CommandeDAO
    abstract fun getPharmacieDAO():PharmacieDAO
    abstract fun getUtilisateurDAO():UtilisateurDAO
    abstract fun getVilleDAO():VilleDAO
}

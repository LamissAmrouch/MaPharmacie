package com.example.android.projet.local_storage

import androidx.room.*
import com.example.android.projet.entities.Commande

@Dao
interface CommandeDAO {
    @Query("select * from commande where idC=:id")
    fun getCommande(id:Int):List<Commande>
    @Insert
    fun addCommande(commande: Commande)
    @Update
    fun updateCommande(commande: Commande)
    @Delete
    fun deleteCommande(commande: Commande)
}

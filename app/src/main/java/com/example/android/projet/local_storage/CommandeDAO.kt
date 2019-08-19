package com.example.android.projet.local_storage

import androidx.room.*
import com.example.android.projet.entities.Commande

@Dao
interface CommandeDAO {
    @Query("select * from commande where idC=:id")
    fun getCommande(id:Int):List<Commande>
    @Query("select * from commande where isSynchronized=0")
    fun getCommandeToSynchronize(): Commande
    @Insert
    fun addCommande(commande: Commande)
    @Update
    fun updateCommande(commande: Commande)
    @Delete
    fun deleteCommande(commande: Commande)

    @Query("select * from commande,pharmacie where id_pharmacie=idP")
    fun getCommandesofPharmacie():List<Commande>

    @Query("select * from commande,utilisateur where id_utilisateur=NSS")
    fun getCommandesofUtilisateur():List<Commande>

    @Query("select * from commande,utilisateur,pharmacie where id_utilisateur=NSS and id_pharmacie=idP")
    fun getCommandes():List<Commande>
}

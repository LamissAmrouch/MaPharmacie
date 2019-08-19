package com.example.android.projet.local_storage

import androidx.room.*
import com.example.android.projet.entities.Utilisateur

@Dao
interface UtilisateurDAO {
    @Query("select * from utilisateur where NSS=:nss")
    fun getUtilisateur(nss:Int):List<Utilisateur>
    @Query("select * from utilisateur where isSynchronized=0")
    fun getUserToSynchronize(): Utilisateur

    @Insert
    fun addUtilisateur(user: Utilisateur)
    @Update
    fun updateUtilisateur(user: Utilisateur)
    @Delete
    fun deleteUtilisateur(user: Utilisateur)

}

package com.example.android.projet.local_storage

import androidx.room.*
import com.example.android.projet.entities.Ville

@Dao
interface VilleDAO {
    @Query("select * from ville where nomV=:nom")
    fun getVillesByNom(nom:String):List<Ville>

    @Query("select * from ville where id=:id")
    fun getVille(id:Int):List<Ville>

    @Insert
    fun addVille(ville: Ville)
    @Update
    fun updateVille(ville: Ville)
    @Delete
    fun deleteVille(ville: Ville)
}

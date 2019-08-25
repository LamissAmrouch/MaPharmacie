package com.example.android.projet.local_storage

import androidx.room.*
import com.example.android.projet.entities.Pharmacie

@Dao
interface PharmacieDAO {
    @Query("select * from pharmacie")
    fun getAllPharmacies():List<Pharmacie>

    @Query("select * from pharmacie where idP=:id")
    fun getPharmacie(id:Int):List<Pharmacie>
    @Query("select * from pharmacie where nom=:nom")
    fun getPharmacieByNom(nom:String):List<Pharmacie>
    @Query("select * from pharmacie where isSynchronized=0")
    fun getPharmacieToSynchronize(): Pharmacie
    @Insert
    fun addPharmacie(pharmacie: Pharmacie)
    @Update
    fun updatePharmacie(pharmacie: Pharmacie)
    @Delete
    fun deletePharmacie(pharmacie: Pharmacie)

    @Query("select * from ville,pharmacie where ville.nomV=:nom and id=id_ville")
    fun getPharmaciesOfVille(nom:String):List<Pharmacie>
}

package com.example.android.projet.db_storage

import com.example.android.projet.entities.Commande
import com.example.android.projet.entities.Pharmacie
import com.example.android.projet.entities.Utilisateur
import com.example.android.projet.entities.Ville
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Endpoint {
    @GET("villes")
    fun getVilles(): Call<List<Ville>>

    @GET("pharmacies")
    fun getPharmacies(): Call<List<Pharmacie>>

    @GET("commandes")
    fun getCommandes(): Call<List<Commande>>

    @GET("users")
    fun getUtilisateurs(): Call<List<Utilisateur>>

    /*@GET("getplayersbyteamname/{team_name}")
    fun getPlayersByTeamName(): Call<List<Player>>*/

    @POST("addville")
    fun addVille(@Body ville:Ville): Call<String>

    @POST("addpharmacie")
    fun addPharmacie(@Body pharmacie:Pharmacie): Call<String>

    @POST("adduser")
    fun addUtilisateur(@Body utilisateur: Utilisateur): Call<String>

    @POST("updateuser")
    fun updateUtilisateur(@Body utilisateur: Utilisateur): Call<String>

    @POST("addcommande")
    fun addCommande(@Body commande: Commande): Call<String>
}
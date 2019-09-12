package com.example.android.projet.db_storage

import com.example.android.projet.entities.Commande
import com.example.android.projet.entities.Pharmacie
import com.example.android.projet.entities.Utilisateur
import com.example.android.projet.entities.Ville
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Endpoint {
    @GET("villes")
    fun getVilles(): Call<List<Ville>>

    @GET("ville/{id}")
    fun getVilleById(@Path("id")id:Int):Call<List<Ville>>

    @GET("user/{numero_tel}/{mot_de_passe}")
    fun getUserByPhone(@Path("numero_tel")numero_tel:Int,
                     @Path("mot_de_passe")mot_de_passe:String):Call<List<Utilisateur>>

    @GET("user/{nss}")
    fun getUserByNSS(@Path("nss")nss:Int):Call<List<Utilisateur>>


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

    @POST("sendSms")
    fun sendSMS(@Body password :String): Call<String>

    @POST("addpharmacie")
    fun addPharmacie(@Body pharmacie:Pharmacie): Call<String>

    @POST("adduser")
    fun addUtilisateur(@Body utilisateur: Utilisateur): Call<String>

    @POST("updateuser")
    fun updateUtilisateur(@Body utilisateur: Utilisateur): Call<String>

    @POST("addcommande")
    fun addCommande(@Body commande: Commande): Call<String>

}
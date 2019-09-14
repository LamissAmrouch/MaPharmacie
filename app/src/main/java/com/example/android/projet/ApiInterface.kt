package com.example.android.projet

import com.example.android.projet.entities.Commande
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiInterface {
    @Multipart
    @POST("commande/ordonnanceImg")
    fun postAvatar(@Part("image") file: MultipartBody.Part): Call<String>

    companion object {
        val ENDPOINT = "http://192.168.0.243:8082/"

        val retrofit = Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
package com.example.android.projet.db_storage

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import androidx.work.impl.utils.futures.SettableFuture
import com.example.android.projet.entities.Pharmacie
import com.example.android.projet.entities.Utilisateur
import com.example.android.projet.entities.Ville
import com.example.android.projet.local_storage.RoomService
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.android.synthetic.main.fragment_inscrire.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Timestamp

class UtilisateurWorker(val ctx: Context, val workParamters: WorkerParameters) : ListenableWorker(ctx, workParamters) {

    lateinit var  future:SettableFuture<Result>

    @SuppressLint("RestrictedApi")
    override fun startWork(): ListenableFuture<Result> {
        future = SettableFuture.create<Result>()
        val user = RoomService.appDatabase.getUtilisateurDAO().getUserToSynchronize(0).get(0)
        addUser(user)
        return future
    }

    private fun addUser(user: Utilisateur) {
        user.isSynchronized=1
        val call = RetrofitService.endpoint.addUtilisateur(user)
        call.enqueue(object : Callback<String> {
            @SuppressLint("RestrictedApi")
            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(ctx,"im here 1",Toast.LENGTH_SHORT).show()
                future.set(Result.retry())
            }
            @SuppressLint("RestrictedApi")
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    RoomService.appDatabase.getUtilisateurDAO().updateUtilisateur(user)
                    future.set(Result.success())
                    Toast.makeText(ctx,"works! user'synchro ="+
                            RoomService.appDatabase.getUtilisateurDAO().getUtilisateur(user.NSS).isSynchronized,
                        Toast.LENGTH_SHORT).show()

                } else {
                    future.set(Result.retry())
                    Toast.makeText(ctx,"im here 2",Toast.LENGTH_SHORT).show()


                }
            }
        })
    }
}
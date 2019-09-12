package com.example.android.projet.db_storage

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import androidx.work.impl.utils.futures.SettableFuture
import com.example.android.projet.entities.Commande
import com.example.android.projet.entities.Pharmacie
import com.example.android.projet.local_storage.RoomService
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.android.synthetic.main.fragment_inscrire.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Timestamp

class CommandeWorker(val ctx: Context, val workParamters: WorkerParameters) : ListenableWorker(ctx, workParamters) {

    lateinit var  future:SettableFuture<Result>

    @SuppressLint("RestrictedApi")
    override fun startWork(): ListenableFuture<Result> {
        future = SettableFuture.create<Result>()
        val commande = RoomService.appDatabase.getCommandeDAO().getCommandeToSynchronize()
        addCommande(commande)
        return future
    }

    private fun addCommande(commande: Commande) {
        val call = RetrofitService.endpoint.addCommande(commande)
        call.enqueue(object : Callback<String> {
            @SuppressLint("RestrictedApi")
            override fun onFailure(call: Call<String>, t: Throwable) {
                future.set(Result.retry())
            }
            @SuppressLint("RestrictedApi")
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    commande.isSynchronized=1
                    RoomService.appDatabase.getCommandeDAO().updateCommande(commande)
                    future.set(Result.success())

                    val call1 = RetrofitService.endpoint.updateCommande(commande)
                    call1.enqueue(object : Callback<String> {
                        override fun onResponse(call: Call<String>, response: Response<String>) {
                            Toast.makeText(ctx,"cmd updated!",Toast.LENGTH_SHORT).show()
                        }
                        override fun onFailure(call: Call<String>, t: Throwable) {
                        }
                    })
                } else {
                    future.set(Result.retry())
                }
            }
        })
    }
}
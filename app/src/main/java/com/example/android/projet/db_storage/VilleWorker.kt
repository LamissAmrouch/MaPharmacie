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

class VilleWorker(val ctx: Context, val workParamters: WorkerParameters) : ListenableWorker(ctx, workParamters) {

    lateinit var  future:SettableFuture<Result>

    @SuppressLint("RestrictedApi")
    override fun startWork(): ListenableFuture<Result> {
        future = SettableFuture.create<Result>()
        val ville = RoomService.appDatabase.getVilleDAO().getVilleToSynchronize()
        addVille(ville)
        return future
    }

    private fun addVille(ville: Ville) {
        val call = RetrofitService.endpoint.addVille(ville)
        call.enqueue(object : Callback<String> {
            @SuppressLint("RestrictedApi")
            override fun onFailure(call: Call<String>, t: Throwable) {
                future.set(Result.retry())
            }
            @SuppressLint("RestrictedApi")
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    ville.isSynchronized=1
                    RoomService.appDatabase.getVilleDAO().updateVille(ville)
                    future.set(Result.success())
                    Toast.makeText(ctx,"works!",Toast.LENGTH_SHORT).show()

                } else {
                    future.set(Result.retry())

                }
            }
        })
    }
}
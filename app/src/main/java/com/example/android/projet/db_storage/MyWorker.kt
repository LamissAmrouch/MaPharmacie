package com.example.android.projet.db_storage

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import androidx.work.impl.utils.futures.SettableFuture
import com.example.android.projet.entities.Pharmacie
import com.example.android.projet.entities.Ville
import com.google.common.util.concurrent.ListenableFuture
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Timestamp

class MyWorker(val ctx: Context, val workParamters: WorkerParameters) :
    ListenableWorker(ctx, workParamters) {
    @SuppressLint("RestrictedApi")
    override fun startWork(): ListenableFuture<Result> {
        val future = SettableFuture.create<Result>()

        val p = Pharmacie("Test", "13éé",
            //Timestamp(123),
          //  Timestamp(124),

            "dfsd", "dfsdf", 1,"dfsd"
        );
        val call2 = RetrofitService.endpoint.addPharmacie(p)
        call2.enqueue(object : Callback<String> {
            @SuppressLint("RestrictedApi")
            override fun onFailure(call: Call<String>, t: Throwable) {
                future.set(Result.retry())
            }
            @SuppressLint("RestrictedApi")
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    future.set(Result.success())
                    Toast.makeText(ctx,"works!",Toast.LENGTH_SHORT).show()

                } else {
                    future.set(Result.retry())

                }
            }
        })
        return future
}
}
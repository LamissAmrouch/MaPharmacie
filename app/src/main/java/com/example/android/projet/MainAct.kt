package com.example.android.projet

import android.content.Intent
import android.os.Bundle
import android.view.ActionMode
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.projet.db_storage.RetrofitService
import com.example.android.projet.entities.Pharmacie
import com.example.android.projet.entities.Ville
import com.example.android.projet.local_storage.RoomService
import androidx.navigation.ui.NavigationUI
import com.example.android.projet.entities.Commande
import kotlinx.android.synthetic.main.content_drawer2.*
import kotlinx.android.synthetic.main.content_menu_profile.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class MainAct : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

    }
    private fun uploadAvatar(userid: String) {
        val file = File("/storage/emulated/0/Pictures/Messenger/received_2485544341722916.jpg")

        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val body:MultipartBody.Part  = MultipartBody.Part.createFormData("image", file.getName(), requestFile)
        val descriptionString = "hello, this is description speaking"
        val description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString)

        val mApiService = ApiInterface.retrofit.create(ApiInterface::class.java)
        val mService = mApiService.postAvatar(description, body)
        mService.enqueue(object : Callback<Commande> {
            override fun onResponse(call: Call<Commande>, response: Response<Commande>) {
                val avatar = response.body()
                //val returnedResponse = avatar.avatar_url
                val returnedResponse =""
                Toast.makeText(this@MainAct, "Returned $returnedResponse", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onFailure(call: Call<Commande>, t: Throwable) {
                call.cancel()
                Toast.makeText(
                    this@MainAct,
                    "Please check your network connection",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

}

package com.example.android.projet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window

import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nav = findNavController(R.id.navHost)
        NavigationUI.setupActionBarWithNavController(this,nav)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHost)
        return navController.navigateUp()
    }


}

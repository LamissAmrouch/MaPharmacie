package com.example.android.projet


import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.TextUtils
import android.text.TextUtils.replace
import android.view.ActionMode
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.View
import kotlinx.android.synthetic.main.content_menu_profile.*



class Menu_profile : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    var  fragment: Fragment = Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_profile)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val call = RetrofitService.endpoint.getPharmacies()
        //var adapter:PharmacieAdapter?=null
        call.enqueue(object: Callback<List<Pharmacie>> {
            override fun onResponse(call: Call<List<Pharmacie>>?, response: Response<List<Pharmacie>>?) {
                if(response?.isSuccessful!!) {
                    val p = response.body()!!
                    val adapter = PharmacieAdapter(this@Menu_profile,p)
                    listPharmacies.adapter = adapter
                    listPharmacies.setOnItemClickListener { adapterView, view, i, l ->
                        val intent = Intent(this@Menu_profile, DrawerActivity2::class.java)
                        intent.putExtra("pos", "i")
                        startActivity(intent)
                    }
                    navView.setNavigationItemSelectedListener(this@Menu_profile)
                }
                else {
                    Toast.makeText(this@Menu_profile,"fail 2!", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Pharmacie>>?, t: Throwable?) {
                Toast.makeText(this@Menu_profile,t?.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_profile, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.nav_mes_pharmacies -> {

                val intent = Intent(this,Menu_profile::class.java)
                startActivity(intent)
            }

            R.id.nav_mes_pharmacies_de_garde -> {
                showHide(listPharmacies)
                val intent = Intent(this,MapActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_mes_commandes-> {

                var fragment = MesCommandesFragment()
                showFragment(fragment)
                showHide(listPharmacies)
            }

            R.id.nav_lancer_commande -> {

                var fragment = NouvelleCommandeFragment()
                showFragment(fragment)
                showHide(listPharmacies)
            }

            R.id.nav_deconnecter -> {
            }

        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }



    fun showFragment(fragment:Fragment)
       {
           val manager = supportFragmentManager
           val transaction = manager.beginTransaction()
           transaction.replace(R.id.container, fragment)
           transaction.addToBackStack(null)
           transaction.commit()
       }

    fun showHide(view: View) {
        if (view.visibility == View.VISIBLE) {

            view.visibility = View.INVISIBLE
        }
    }

    fun removeFragment(fragment:Fragment)
    {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.remove(fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

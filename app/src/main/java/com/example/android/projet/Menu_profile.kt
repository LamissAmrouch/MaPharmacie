package com.example.android.projet


import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.TextUtils
import android.text.TextUtils.replace
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.widget.TableLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.content_menu_profile.*
import kotlinx.android.synthetic.main.fragment_list_pharmacie.*

class Menu_profile : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    var fragment: Fragment = Fragment ()

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

        navView.setNavigationItemSelectedListener(this)


       val nav = findNavController(R.id.navHost2)
       NavigationUI.setupActionBarWithNavController(this,nav)

    }

    //Activer l'action du BackButton
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHost2)
        return navController.navigateUp()
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.nav_mes_pharmacies -> {
                var fragment = ListPharmacieCFragment()
                  showFragment(fragment)
                val nav = findNavController(R.id.navHost2)
                NavigationUI.setupActionBarWithNavController(this,nav)
            }

            R.id.nav_mes_pharmacies_de_garde -> {
                var fragment = ListPharmacieCFragment()
                 // showFragment(fragment)
                val nav = findNavController(R.id.navHost2)
                NavigationUI.setupActionBarWithNavController(this,nav)
            }

            R.id.nav_mes_commandes-> {
                var fragment = MesInfosFragment()
                showFragment(fragment)

                val nav = findNavController(R.id.navHost2)
                NavigationUI.setupActionBarWithNavController(this,nav)
            }

            R.id.nav_lancer_commande -> {
            }

            R.id.nav_mon_profil -> {
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

    fun getData(): List<Pharm> {
        val list = mutableListOf<Pharm>()
        list.add(
            Pharm(
                "pharmacie1",
                "Bab Zouar",
                "De 8h à 21h",
                "7/7",
                "0558757779",
                "www.facebook.com/MaPharmacie",
                "www.googlemap.com/MaPharmacie"
            )
        )
        list.add(
            Pharm(
                "pharmacie2",
                "Oued Smar",
                "De 8h à 21h",
                "7/7",
                "0558757779",
                "www.facebook.com/MaPharmacie",
                "www.googlemap.com/MaPharmacie"

            )
        )
        list.add(
            Pharm(
                "pharmacie3",
                "Harrache",
                "De 8h à 21h",
                "7/7",
                "0558757779",
                "www.facebook.com/MaPharmacie",
                "www.googlemap.com/MaPharmacie"

            )
        )
        list.add(
            Pharm(
                "pharmacie4",
                "Ben Aknoun",
                "De 8h à 21h",
                "7/7",
                "0558757779",
                "www.facebook.com/MaPharmacie",
                "www.googlemap.com/MaPharmacie"

            )
        )
        list.add(
            Pharm(
                "pharmacie5",
                "Tipaza",
                "De 8h à 21h",
                "7/7",
                "0558757779",
                "www.facebook.com/MaPharmacie",
                "www.googlemap.com/MaPharmacie"

            )
        )
        list.add(
            Pharm(
                "pharmacie6",
                "Rouiba",
                "De 8h à 21h",
                "7/7",
                "0558757779",
                "www.facebook.com/MaPharmacie",
                "www.googlemap.com/MaPharmacie"

            )
        )
        return list
    }


}

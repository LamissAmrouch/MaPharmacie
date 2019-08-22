package com.example.android.projet

import android.content.Intent
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.content_drawer2.*



class DrawerActivity2 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var  fragment: Fragment = Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer2)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val pos = intent.getIntExtra("pos",0)

        val list = getData()

        nom.text = list.get(pos).nom
        adresse.text = list.get(pos).adresse
        horaire.text = list.get(pos).horaireO
        jours.text = list.get(pos).joursO
        phone.text = list.get(pos). numeroTel
        pageFB.text = list.get(pos).pageFB
        localisation.text = list.get(pos).localisation


        navView.setNavigationItemSelectedListener(this)

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
        menuInflater.inflate(R.menu.drawer_activity2, menu)
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

                val intent = Intent(this,Menu_profile::class.java)
                startActivity(intent)
            }

            R.id.nav_mes_pharmacies_de_garde -> {
                val intent = Intent(this,MapActivity::class.java)
                startActivity(intent)

            }

            R.id.nav_mes_commandes-> {
                var fragment = MesCommandesFragment()
                showFragment(fragment)
                showHide(nom)
                showHide(horaire)
                showHide(jours)
                showHide(adresse)
                showHide(phone)
                showHide(localisation)
                showHide(pageFB)
                showHide(textView10)
                showHide(textView12)
                showHide(textView14)
                showHide(textView16)
                showHide(textView8)
                showHide(textView18)

            }



            R.id.nav_lancer_commande -> {

                var fragment = NouvelleCommandeFragment()
                showFragment(fragment)
                showHide(nom)
                showHide(horaire)
                showHide(jours)
                showHide(adresse)
                showHide(phone)
                showHide(localisation)
                showHide(pageFB)
                showHide(textView10)
                showHide(textView12)
                showHide(textView14)
                showHide(textView16)
                showHide(textView8)
                showHide(textView18)
            }

            R.id.nav_mon_profil -> {

                var fragment = MonProfilFragment()
                showFragment(fragment)
                showHide(nom)
                showHide(horaire)
                showHide(jours)
                showHide(adresse)
                showHide(phone)
                showHide(localisation)
                showHide(pageFB)
                showHide(textView10)
                showHide(textView12)
                showHide(textView14)
                showHide(textView16)
                showHide(textView8)
                showHide(textView18)
            }

            R.id.nav_deconnecter -> {
            }

        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun showFragment(fragment: Fragment)
    {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.container2, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun showHide(view: View) {
        if (view.visibility == View.VISIBLE) {

            view.visibility = View.INVISIBLE
        }
    }

    fun removeFragment(fragment: Fragment)
    {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.remove(fragment)
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

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
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.projet.db_storage.RetrofitService
import com.example.android.projet.entities.Pharmacie
import com.example.android.projet.entities.Ville
import com.example.android.projet.local_storage.RoomService
import kotlinx.android.synthetic.main.content_drawer2.*
import kotlinx.android.synthetic.main.fragment_list_pharmacie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DrawerActivity2 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var  fragment: Fragment = Fragment()
    lateinit var listdata:List<Pharmacie>
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

        val call = RetrofitService.endpoint.getPharmacies()
        call.enqueue(object: Callback<List<Pharmacie>> {
            override fun onResponse(call: Call<List<Pharmacie>>?, response: Response<List<Pharmacie>>?) {
                if(response?.isSuccessful!!) {
                    val list = response.body()!!
                    nom.text = list.get(pos).nom
                    adresse.text = list.get(pos).adresse
                    horaire_ouverture.text = "2015"//list.get(pos).horaire_ouverture
                    horaire_fermeture.text ="2019" //list.get(pos).horaire_fermeture
                    caisse.text = list.get(pos).caisse
                    ville.text =findVille(list.get(pos).id_ville!!).nomV
                    lien_fb.text = list.get(pos).lien_fb
                    lien_localisation.text = list.get(pos).lien_localisation

                    navView.setNavigationItemSelectedListener(this@DrawerActivity2)
                }
                else {
                    Toast.makeText(this@DrawerActivity2,"fail 2!", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Pharmacie>>?, t: Throwable?) {
                Toast.makeText(this@DrawerActivity2,t?.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun findVille(id:Int): Ville {
        return RoomService.appDatabase.getVilleDAO().getVille(id).get(0)
    }

    /* override fun onSupportNavigateUp(): Boolean {
         val navController = findNavController(R.id.navHost)
         return navController.navigateUp()
     }*/


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
                var fragment = PharmaciesGardesFragment()
                showFragment(fragment)
                showHide(nom)
                showHide(horaire_ouverture)
                showHide(horaire_fermeture)
                showHide(adresse)
                showHide(caisse)
                showHide(lien_localisation)
                showHide(lien_fb)
                showHide(textView10)
                showHide(textView12)
                showHide(textView14)
                showHide(textView16)
                showHide(textView8)
                showHide(textView18)

            }

            R.id.nav_mes_commandes-> {
                var fragment = MesCommandesFragment()
                showFragment(fragment)
                showHide(nom)
                showHide(horaire_ouverture)
                showHide(horaire_fermeture)
                showHide(adresse)
                showHide(caisse)
                showHide(lien_localisation)
                showHide(lien_fb)
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
                showHide(horaire_ouverture)
                showHide(horaire_fermeture)
                showHide(adresse)
                showHide(caisse)
                showHide(lien_localisation)
                showHide(lien_fb)
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
                showHide(horaire_ouverture)
                showHide(horaire_fermeture)
                showHide(adresse)
                showHide(caisse)
                showHide(lien_localisation)
                showHide(lien_fb)
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

}

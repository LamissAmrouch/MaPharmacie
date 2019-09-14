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
import kotlinx.android.synthetic.main.content_drawer2.*
import kotlinx.android.synthetic.main.content_menu_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val nss = intent.getIntExtra("nss",0)
        val menu:Menu=navView.menu
        if(nss!=0){
            //nav_menu.findItem(R.id.nav_settings).setVisible(false);

            menu.findItem(R.id.nav_lancer_commande).setVisible(true)
            menu.findItem(R.id.nav_mes_commandes).setVisible(true)
            menu.findItem(R.id.nav_deconnecter).setVisible(true)
        }
        else{
            menu.findItem(R.id.nav_lancer_commande).setVisible(false)
            menu.findItem(R.id.nav_mes_commandes).setVisible(false)
            menu.findItem(R.id.nav_deconnecter).setVisible(false)
        }

        emptyfield()

        val call = RetrofitService.endpoint.getPharmacies()
        call.enqueue(object: Callback<List<Pharmacie>> {
            override fun onResponse(call: Call<List<Pharmacie>>?, response: Response<List<Pharmacie>>?) {
                if(response?.isSuccessful!!) {
                    val list = response.body()!!
                    nom.text = list.get(pos).nom
                    adresse.text = list.get(pos).adresse
                    horaire_ouverture.text = list.get(pos).horaire_ouverture
                    horaire_fermeture.text = list.get(pos).horaire_fermeture
                    caisse.text = list.get(pos).caisse
                    val call1 = RetrofitService.endpoint.getVilleById(list.get(pos).id_ville!!)
                    call1.enqueue(object: Callback<List<Ville>> {
                        override fun onResponse(call: Call<List<Ville>>?, response: Response<List<Ville>>?) {
                            if(response?.isSuccessful!!) {

                                ville.text =response.body()?.get(0)?.nomV!!
                            }
                            else {
                                Toast.makeText(this@DrawerActivity2,"fail ville!", Toast.LENGTH_SHORT).show()
                            }
                        }
                        override fun onFailure(call: Call<List<Ville>>?, t: Throwable?) {
                            Toast.makeText(this@DrawerActivity2,t?.message, Toast.LENGTH_SHORT).show()
                        }
                    })


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

    private fun emptyfield() {
        nom.text=""
        adresse.text =""
        horaire_ouverture.text = ""//list.get(pos).horaire_ouverture
        horaire_fermeture.text ="" //list.get(pos).horaire_fermeture
        caisse.text = ""
        ville.text=""
        lien_fb.text=""
        lien_localisation.text=""
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.nav_page_accuiel-> {
                val nss = intent.getIntExtra("nss",0)
                val intent = Intent(this,MainActivity::class.java)
                intent.putExtra("nss",nss )
                startActivity(intent)
            }

            R.id.nav_mes_pharmacies -> {
                val nss = intent.getIntExtra("nss",0)
                val intent = Intent(this,Menu_profile::class.java)
                intent.putExtra("nss",nss )
                startActivity(intent)
            }

            R.id.nav_mes_pharmacies_de_garde -> {
                val nss = intent.getIntExtra("nss",0)
                val intent = Intent(this,MapActivity::class.java)
                intent.putExtra("nss",nss )
                startActivity(intent)
            }

            R.id.nav_mes_commandes-> {
                val nss = intent.getIntExtra("nss",0)
                var bundle = bundleOf("nss" to nss)
                var fragment = MesCommandesFragment()

                fragment.arguments=bundle


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
                val nss = intent.getIntExtra("nss",0)
                var bundle = bundleOf("nss" to nss)

                var fragment = NouvelleCommandeFragment()
                fragment.arguments=bundle

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
                val intent = Intent(this,MainActivity::class.java)
                intent.putExtra("nss", 0)
                startActivity(intent)
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

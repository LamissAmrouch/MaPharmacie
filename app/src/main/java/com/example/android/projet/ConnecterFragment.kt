package com.example.android.projet
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.android.projet.db_storage.RetrofitService
import com.example.android.projet.entities.Pharmacie
import com.example.android.projet.entities.Utilisateur
import com.example.android.projet.local_storage.RoomService
import kotlinx.android.synthetic.main.content_menu_profile.*
import kotlinx.android.synthetic.main.fragment_connecter.*
import kotlinx.android.synthetic.main.fragment_connecter.caisse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConnecterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_connecter, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        connecterBtn.setOnClickListener { view ->
            val call = RetrofitService.endpoint.getUserByPhone(
                Integer.valueOf(caisse.text.toString()),
                mdp.text.toString()
            )
            call.enqueue(object: Callback<List<Utilisateur>> {
                override fun onResponse(call: Call<List<Utilisateur>>?, response: Response<List<Utilisateur>>?) {
                    if(response?.isSuccessful!!) {
                        val user = response.body()!!.get(0)
                        if (user != null) {
                            caisse.text.clear()
                            mdp.text.clear()

                            val nss = arguments?.getInt("nss")
                            val firstTime = user.first
                            if (arguments?.getInt("nss") != null || firstTime==1)
                            {
                                var bundle = bundleOf("nss" to user.NSS)
                                view.findNavController().navigate(R.id.action_connecterFragment_to_changementMdpFragment,bundle)
                            }
                            else // la premiere connexion il doit changer son mot de passe
                            {
                                //var bundle = bundleOf("nss" to user.NSS)
                                val intent = Intent(context, Menu_profile::class.java)
                                intent.putExtra("nss", nss);
                                startActivity(intent)
                                //view.findNavController().navigate(R.id.action_connecterFragment_to_menu_profile2,bundle)
                            }

                        } else{
                            Toast.makeText(activity, "Erreur d'authentification", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else {
                        Toast.makeText(activity!!,"fail 2!", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<List<Utilisateur>>?, t: Throwable?) {
                    Toast.makeText(activity!!,t?.message, Toast.LENGTH_SHORT).show()
                }
            })

                /* var user = RoomService.appDatabase.getUtilisateurDAO().findExistingUser(
                Integer.valueOf(caisse.text.toString()),
                mdp.text.toString()
            )*/
        }

    }
}

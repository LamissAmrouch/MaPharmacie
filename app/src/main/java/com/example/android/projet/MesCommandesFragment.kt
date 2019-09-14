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
import com.example.android.projet.entities.Commande
import com.example.android.projet.entities.Pharmacie
import kotlinx.android.synthetic.main.content_menu_profile.*
import kotlinx.android.synthetic.main.fragment_mes_commandes.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MesCommandesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mes_commandes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var nss = arguments?.getInt("nss")
        val call = RetrofitService.endpoint.getCommandesByIdUser(nss!!)
        /* get nss and resend it */


        call.enqueue(object: Callback<List<Commande>> {
            override fun onResponse(call: Call<List<Commande>>?, response: Response<List<Commande>>?) {
                if(response?.isSuccessful!!) {
                    val p = response.body()!!
                    val adapter = CommandeAdapter(activity!!,p)
                    listCommandes.adapter = adapter
                }
                else {
                    Toast.makeText(activity!!,"fail 2!", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Commande>>?, t: Throwable?) {
                Toast.makeText(activity!!,t?.message, Toast.LENGTH_SHORT).show()
            }
        })

    }
}

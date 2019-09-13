package com.example.android.projet


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.android.projet.db_storage.RetrofitService
import com.example.android.projet.entities.Utilisateur
import com.example.android.projet.local_storage.AppDatabase
import com.example.android.projet.local_storage.RoomService
import kotlinx.android.synthetic.main.fragment_changement_mdp.*
import kotlinx.android.synthetic.main.fragment_connecter.*
import kotlinx.android.synthetic.main.fragment_fragment1.*
import android.content.Intent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChangementMdpFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
             return inflater.inflate(R.layout.fragment_changement_mdp, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        confirmChgMdp.setOnClickListener { view ->
            var nss = arguments?.getInt("nss")

            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("nss", nss);

            var oldMdp = ancienMdp.text.toString()
            var newMdp1 = nouveauMdp1.text.toString()
            var newMdp2 = nouveauMdp2.text.toString()
            //Toast.makeText(activity, "nss = "+ nss!! + "oldMdp = "+ oldMdp, Toast.LENGTH_SHORT).show()
            if(newMdp1.equals(newMdp2)){
                val call = RetrofitService.endpoint.getUserByNSS(
                    nss!!)
                call.enqueue(object: Callback<List<Utilisateur>> {
                    override fun onResponse(call: Call<List<Utilisateur>>?, response: Response<List<Utilisateur>>?) {
                        if(response?.isSuccessful!!) {
                            val user = response.body()!!.get(0)
                            if (user != null) {
                                user.mot_de_passe= newMdp1
                                user.first=0

                                val call2 = RetrofitService.endpoint.updateUtilisateur(user,nss)
                                call2.enqueue(object: Callback<String> {
                                    override fun onResponse(call: Call<String>?, response: Response<String>?) {
                                        if (response?.isSuccessful!!) {
                                            Toast.makeText(
                                                activity,
                                                "success updated",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            val intent = Intent(context, Menu_profile::class.java)
                                            intent.putExtra("nss", nss);
                                            startActivity(intent)
                                        }
                                    }
                                    override fun onFailure(call: Call<String>?, t: Throwable?) {
                                        Toast.makeText(activity!!,t?.message, Toast.LENGTH_SHORT).show()
                                    }
                                 })
                            }else{
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

               }
            else {
                   Toast.makeText(activity, "Les mots de passe ne sont pas similaires", Toast.LENGTH_SHORT).show()
               }
        }
    }
}

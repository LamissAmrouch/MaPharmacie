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
            var oldMdp = ancienMdp.text.toString()
            var newMdp1 = nouveauMdp1.text.toString()
            var newMdp2 = nouveauMdp2.text.toString()
            Toast.makeText(activity, "nss = "+ nss!! + "oldMdp = "+ oldMdp, Toast.LENGTH_SHORT).show()

            if (RoomService.appDatabase.getUtilisateurDAO().checkOldPassword(nss!!, oldMdp) != null) {
                if(newMdp1.equals(newMdp2)){
                    var user = RoomService.appDatabase.getUtilisateurDAO().getUtilisateur(nss)
                    user.mot_de_passe= newMdp1
                    user.first=0
                    RoomService.appDatabase.getUtilisateurDAO().updateUtilisateur(user)
                    RetrofitService.endpoint.updateUtilisateur(user)
                    view.findNavController().navigate(R.id.action_changementMdpFragment_to_menu_profile2,bundleOf("nss" to nss))
                }
                else {
                    Toast.makeText(activity, "Les mots de passe ne sont pas similaires", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(activity, "mot de passe erroné", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

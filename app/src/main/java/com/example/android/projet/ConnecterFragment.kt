package com.example.android.projet
import android.app.Person
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.android.projet.entities.Utilisateur
import com.example.android.projet.local_storage.AppDatabase
import com.example.android.projet.local_storage.RoomService
import kotlinx.android.synthetic.main.content_drawer2.*
import kotlinx.android.synthetic.main.fragment_connecter.*
import kotlinx.android.synthetic.main.fragment_connecter.phone

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
            var user = RoomService.appDatabase.getUtilisateurDAO().findExistingUser(
                Integer.valueOf(phone.text.toString()),
                mdp.text.toString()
            )
            if (user != null) {
                phone.text.clear()
                mdp.text.clear()

                val nss = arguments?.getInt("nss")
                val firstTime = user.first
                if (arguments?.getInt("nss") != null || firstTime==1)
                {
                    var bundle = bundleOf("nss" to nss)
                    view.findNavController().navigate(R.id.action_connecterFragment_to_changementMdpFragment,bundle)
                }
                else // la premiere connexion il doit changer son mot de passe
                {
                    var bundle = bundleOf("nss" to user.NSS)
                    view.findNavController().navigate(R.id.action_connecterFragment_to_menu_profile2,bundle)
                }

            }
            else{
                Toast.makeText(activity, "Erreur d'authentification", Toast.LENGTH_SHORT).show()
            }


        }

    }
}

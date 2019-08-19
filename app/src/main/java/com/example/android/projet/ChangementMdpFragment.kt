package com.example.android.projet


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.android.projet.entities.Utilisateur
import com.example.android.projet.local_storage.AppDatabase
import kotlinx.android.synthetic.main.fragment_changement_mdp.*
import kotlinx.android.synthetic.main.fragment_connecter.*
import kotlinx.android.synthetic.main.fragment_fragment1.*
import org.jetbrains.anko.support.v4.toast


class ChangementMdpFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
             return inflater.inflate(R.layout.fragment_changement_mdp, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        var mDB: AppDatabase? = null
        super.onActivityCreated(savedInstanceState)
        confirmChgMdp.setOnClickListener { view ->
            var nss = arguments?.getInt("nss")
            var oldMdp = ancienMdp.text.toString()
            var newMdp1 = nouveauMdp1.text.toString()
            var newMdp2 = nouveauMdp2.text.toString()
            if (mDB?.getUtilisateurDAO()?.findExistingUser(nss, oldMdp) != null) {
                if(newMdp1.equals(newMdp2)){
                    var user = mDB.getUtilisateurDAO().getUtilisateur(nss)
                    user.mot_de_passe= newMdp1
                    user.isSynchronized= 0
                    mDB.getUtilisateurDAO().updateUtilisateur(user)
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

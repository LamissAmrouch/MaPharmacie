package com.example.android.projet


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_connecter.connecterBtn
import kotlinx.android.synthetic.main.fragment_inscrire.*

class InscrireFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inscrire, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        connecterBtn.setOnClickListener { view ->
            //view.findNavController().navigate(R.id.action_inscrireFragment_to_connecterFragment)
            Toast.makeText(activity, "Merci pour votre inscription à Ma Pharmacie", Toast.LENGTH_SHORT).show()
            Toast.makeText(activity, "Vous allez recevoir dans quelques secondes un SMS contenant votre mot de passe", Toast.LENGTH_SHORT).show()

        }

        compteExistant.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_inscrireFragment_to_connecterFragment)
        }
    }


}

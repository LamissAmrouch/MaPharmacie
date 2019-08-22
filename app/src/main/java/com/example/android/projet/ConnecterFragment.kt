package com.example.android.projet
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_connecter.*

class ConnecterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_connecter, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val one: Int
        one = 1
        connecterBtn.setOnClickListener { view ->
            if ( one == 0 ) // la premiere connexion il doit changer son mot de passe
            {
                view.findNavController().navigate(R.id.action_connecterFragment_to_changementMdpFragment)
            }
            else
            {
                view.findNavController().navigate(R.id.action_connecterFragment_to_menu_profile2)
            }

        }

    }
}

package com.example.android.projet


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_list_pharmacie.*
import kotlinx.android.synthetic.main.fragment_mes_commandes.*


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

        val adapter = CommandeAdapter(activity!!,getData())
        listCommandes.adapter = adapter

    }

    fun getData(): List<cmd> {
        val list = mutableListOf<cmd>()
        list.add(
            cmd(
                "Commande 1",
                "01/02/2019",
                "Reçu"
            )
        )
        list.add(
            cmd(
                "Commande 2",
                "11/08/2019",
                "Traité"
            )
        )
        list.add(
            cmd(
                "Commande 3",
                "11/09/2019",
                "En cours"
            )
        )

        return list
    }


}

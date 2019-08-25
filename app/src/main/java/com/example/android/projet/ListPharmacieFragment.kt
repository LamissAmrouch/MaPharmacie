package com.example.android.projet


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.android.projet.entities.Pharmacie
import com.example.android.projet.local_storage.RoomService
import kotlinx.android.synthetic.main.fragment_connecter.*
import kotlinx.android.synthetic.main.fragment_list_pharmacie.*


class ListPharmacieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_pharmacie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

            val adapter = PharmacieAdapter(activity!!,getData())
            listPharmacies.adapter = adapter
            listPharmacies.setOnItemClickListener { adapterView, view, i, l ->
            var bundle = bundleOf("pos" to i)
            view.findNavController().navigate(R.id.action_listPharmacieFragment_to_detailsPharmacieFragment, bundle)
        }
    }

    fun getData(): List<Pharmacie> {
        val list = RoomService.appDatabase.getPharmacieDAO().getAllPharmacies()
        return list

        /*list.add(
            Pharm(
                "pharmacie1",
                "Bab Zouar",
                "De 8h à 21h",
                "7/7",
                "0558757779",
                "www.facebook.com/MaPharmacie",
                "www.googlemap.com/MaPharmacie"
                )
        )
        list.add(
            Pharm(
                "pharmacie2",
                "Oued Smar",
                "De 8h à 21h",
                "7/7",
                "0558757779",
                "www.facebook.com/MaPharmacie",
                "www.googlemap.com/MaPharmacie"

            )
        )
        list.add(
            Pharm(
                "pharmacie3",
                "Harrache",
                "De 8h à 21h",
                "7/7",
                "0558757779",
                "www.facebook.com/MaPharmacie",
                "www.googlemap.com/MaPharmacie"

            )
        )
        list.add(
            Pharm(
                "pharmacie4",
                "Ben Aknoun",
                "De 8h à 21h",
                "7/7",
                "0558757779",
                "www.facebook.com/MaPharmacie",
                "www.googlemap.com/MaPharmacie"

            )
        )
        list.add(
            Pharm(
                "pharmacie5",
                "Tipaza",
                "De 8h à 21h",
                "7/7",
                "0558757779",
                "www.facebook.com/MaPharmacie",
                "www.googlemap.com/MaPharmacie"

            )
        )
        list.add(
            Pharm(
                "pharmacie6",
                "Rouiba",
                "De 8h à 21h",
                "7/7",
                "0558757779",
                "www.facebook.com/MaPharmacie",
                "www.googlemap.com/MaPharmacie"

            )
        ) */
    }

}

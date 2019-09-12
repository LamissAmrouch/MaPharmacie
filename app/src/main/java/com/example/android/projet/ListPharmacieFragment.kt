package com.example.android.projet


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.android.projet.db_storage.RetrofitService
import com.example.android.projet.entities.Pharmacie
import com.example.android.projet.local_storage.RoomService
import kotlinx.android.synthetic.main.fragment_connecter.*
import kotlinx.android.synthetic.main.fragment_list_pharmacie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
        val call = RetrofitService.endpoint.getPharmacies()
        call.enqueue(object: Callback<List<Pharmacie>> {
            override fun onResponse(call: Call<List<Pharmacie>>?, response: Response<List<Pharmacie>>?) {
                if(response?.isSuccessful!!) {
                    val p = response.body()!!
                    val adapter = PharmacieAdapter(activity!!,p)
                    listPharmacies.adapter = adapter
                    listPharmacies.setOnItemClickListener { adapterView, view, i, l ->
                        var bundle = bundleOf("pos" to i)
                        view.findNavController().navigate(R.id.action_listPharmacieFragment_to_detailsPharmacieFragment, bundle)
                    }
                }
                else {
                    Toast.makeText(activity,"fail 2!", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Pharmacie>>?, t: Throwable?) {
                Toast.makeText(activity,t?.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
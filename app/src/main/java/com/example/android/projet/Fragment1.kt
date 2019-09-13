package com.example.android.projet


import android.content.Intent
import android.os.Bundle
import android.telephony.SmsManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
/*import com.twilio.http.TwilioRestClient
import com.twilio.rest.api.v2010.account.MessageCreator
import com.twilio.type.PhoneNumber */
import kotlinx.android.synthetic.main.fragment_fragment1.*
import org.jetbrains.anko.support.v4.intentFor


class Fragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment1, container, false)

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var nss = arguments?.getInt("nss")

        var bundle = bundleOf("nss" to nss)
        commencer.setOnClickListener { view ->
             /* send nss to activity */
            val intent = Intent(context, Menu_profile::class.java)
            intent.putExtra("nss", nss);
            startActivity(intent)

            //view.findNavController().navigate(R.id.action_fragment1_to_menu_profile2)
        }

        inscrire.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_fragment1_to_inscrireFragment)
        }

        connecter.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_fragment1_to_connecterFragment,bundle)
        }
    }

}

package com.example.android.projet


import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.navigation.findNavController
import com.example.android.projet.local_storage.RoomService
import com.twilio.http.TwilioRestClient
import com.twilio.rest.api.v2010.account.Message
import com.twilio.rest.api.v2010.account.MessageCreator
import com.twilio.type.PhoneNumber
/*import com.twilio.http.TwilioRestClient
import com.twilio.rest.api.v2010.account.MessageCreator
import com.twilio.type.PhoneNumber */
import kotlinx.android.synthetic.main.fragment_fragment1.*
import android.widget.Toast
import androidx.work.ListenableWorker
import com.example.android.projet.db_storage.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.floor
import kotlin.random.Random


class Fragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment1, container, false)

    }

    @SuppressLint("UnlocalizedSms")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        commencer.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_fragment1_to_listPharmacieFragment)
        }

        inscrire.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_fragment1_to_inscrireFragment)
        }

        connecter.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_fragment1_to_connecterFragment)
        }
    }

}

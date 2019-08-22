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
           // view.findNavController().navigate(R.id.action_fragment1_to_listPharmacieFragment)
          // view.findNavController().navigate(R.id.action_fragment1_to_menu_profile2)
            try{
                val call =RetrofitService.endpoint.sendSMS("123")
                call.enqueue(object:Callback<String> {
                    override fun onResponse(call: Call<String>?, response: Response<String>?) {
                       if (response?.isSuccessful!!) Toast.makeText(activity, "SMS Sent Successfully", Toast.LENGTH_SHORT).show()
                        else  Toast.makeText(activity, "SMS Failed to Send, Please try again1", Toast.LENGTH_SHORT).show()
                    }
                    override fun onFailure(call: Call<String>?, t: Throwable?) {
                        Toast.makeText(activity, "SMS Failed to Send, Please try again2", Toast.LENGTH_SHORT).show()

                    }
                })
            }
            catch(e: Exception){
                Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
            }
      /*      try {
                PackageManager.PERMISSION_GRANTED
                var obj = SmsManager.getDefault()
                obj.sendTextMessage("(+213)557-28-05-07",null, "Welcome to Ma pharmacie",null , null)
                Toast.makeText(activity, "SMS Sent Successfully", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(activity, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show()
            }
*/
/*
            val TWILIO_ACCOUNT_SID= "AC0a573be46c16b3d941b566c1aec17156"
            val TWILIO_AUTO_TOKEN = "e1f0f7b92e4944dc68bab33bd1152d55"
            val MY_PHONE_NUMBER = PhoneNumber("+15005550006")
            val client = TwilioRestClient.Builder(TWILIO_ACCOUNT_SID,TWILIO_AUTO_TOKEN).build()

            val message: Message = MessageCreator(
                MY_PHONE_NUMBER,
                PhoneNumber("+213558878809"),
                "Merci pour inscrire à ma pharmacie"
            ).create(client) */

        }

        inscrire.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_fragment1_to_inscrireFragment)
        }

        connecter.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_fragment1_to_connecterFragment)
        }
    }



}

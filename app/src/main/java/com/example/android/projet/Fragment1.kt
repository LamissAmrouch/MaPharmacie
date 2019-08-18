package com.example.android.projet


import android.os.Bundle
import android.telephony.SmsManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.navigation.findNavController
/*import com.twilio.http.TwilioRestClient
import com.twilio.rest.api.v2010.account.MessageCreator
import com.twilio.type.PhoneNumber */
import kotlinx.android.synthetic.main.fragment_fragment1.*



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

        commencer.setOnClickListener { view ->
           // view.findNavController().navigate(R.id.action_fragment1_to_listPharmacieFragment)
           view.findNavController().navigate(R.id.action_fragment1_to_menu_profile2)

            //var obj = SmsManager.getDefault()
            //obj.sendTextMessage("558757779",null, "Welcome to Ma pharmacie",null , null)


          /*  val TWILIO_ACCOUNT_SID= "AC5f10b34cac82dfe6a3a397bfaec4870c"
            val TWILIO_AUTO_TOKEN = "b041bae2f3649ced48d1f3104fd67c90"
            val MY_PHONE_NUMBER = "+213558757779"
            val client = TwilioRestClient.Builder(TWILIO_ACCOUNT_SID,TWILIO_AUTO_TOKEN).build()

            val message = MessageCreator(
                PhoneNumber("+213558757779"),
                PhoneNumber("+213796951450"),
                "Merci pour inscrire à ma pharmacie"
            ).create(client)

            println(message.sid) */
        }

        inscrire.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_fragment1_to_inscrireFragment)
        }

        connecter.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_fragment1_to_connecterFragment)
        }
    }



}

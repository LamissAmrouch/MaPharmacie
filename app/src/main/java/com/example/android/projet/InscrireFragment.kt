package com.example.android.projet


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.work.*
import com.example.android.projet.db_storage.RetrofitService
import com.example.android.projet.db_storage.UtilisateurWorker
import com.example.android.projet.entities.Utilisateur
import com.example.android.projet.local_storage.RoomService
import kotlinx.android.synthetic.main.fragment_connecter.connecterBtn
import kotlinx.android.synthetic.main.fragment_inscrire.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random


class InscrireFragment : Fragment() {
    lateinit var user: Utilisateur
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
            val pass = generateRandomPassword()
            user = Utilisateur(
                nom.text.toString(),
                prenom.text.toString(),
                adresse.text.toString(),
                Integer.valueOf(caisse.text.toString()),
                pass,
                Integer.valueOf(nss.text.toString())
            )
            RoomService.appDatabase.getUtilisateurDAO().addUtilisateur(user)
            sensSMS(pass)

            sync()

            var bundle = bundleOf("nss" to user.NSS)
            Toast.makeText(
                activity,
                "Inscription Réussi !\n" +
                        "Vous recevrez votre code en SMS",
                Toast.LENGTH_SHORT
            ).show()

            nom.text.clear()
            prenom.text.clear()
            adresse.text.clear()
            caisse.text.clear()
            nss.text.clear()

            view.findNavController()
                .navigate(R.id.action_inscrireFragment_to_connecterFragment, bundle)
        }

        compteExistant.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_inscrireFragment_to_connecterFragment)
        }
    }

    private fun sensSMS(password: String) {
        try {
            val call = RetrofitService.endpoint.sendSMS(password)
            call.enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>?, response: Response<String>?) {
                    if (response?.isSuccessful!!) {
                       // Toast.makeText(      activity, "Vous recevrez votre code en SMS",Toast.LENGTH_SHORT).show()
                    } //else Toast.makeText(activity, "SMS non envoyé ", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<String>?, t: Throwable?) {
                    Toast.makeText(activity, "SMS non envoyé ", Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: Exception) {
            Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun generateRandomPassword(): String {
        val chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        var passWord = ""
        for (i in 0..10) {
            passWord += chars[Random.nextInt(0, chars.length)]
        }
        return passWord
    }

    private fun sync() {
        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.UNMETERED).build()
        val req = OneTimeWorkRequest.Builder(UtilisateurWorker::class.java).addTag(
            "td1"
        ).setConstraints(
            constraints
        ).build()
        val workManager = WorkManager.getInstance()
        workManager.enqueueUniqueWork("work", ExistingWorkPolicy.REPLACE, req)
    }

}

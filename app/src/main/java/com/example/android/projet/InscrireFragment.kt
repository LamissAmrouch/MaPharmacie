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
import com.example.android.projet.db_storage.UtilisateurWorker
import com.example.android.projet.entities.Utilisateur
import com.example.android.projet.local_storage.AppDatabase
import com.example.android.projet.local_storage.RoomService
import kotlinx.android.synthetic.main.fragment_connecter.connecterBtn
import kotlinx.android.synthetic.main.fragment_inscrire.*


class InscrireFragment : Fragment() {
    lateinit var user :Utilisateur
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
            user = Utilisateur(
                nom.text.toString(),
                prenom.text.toString(),
                adresse.text.toString(),
                Integer.valueOf(phone.text.toString()),
                "123",
                Integer.valueOf(nss.text.toString())
            )
            RoomService.appDatabase.getUtilisateurDAO().addUtilisateur(user)
            nom.text.clear()
            prenom.text.clear()
            adresse.text.clear()
            phone.text.clear()
            nss.text.clear()
            sync()

            var bundle = bundleOf("nss" to user.NSS)
            //Toast.makeText(activity, "Merci pour votre inscription à Ma Pharmacie", Toast.LENGTH_SHORT).show()
            //Toast.makeText(activity, "Vous allez recevoir dans quelques secondes un SMS contenant votre mot de passe", Toast.LENGTH_SHORT).show()
            view.findNavController().navigate(R.id.action_inscrireFragment_to_connecterFragment,bundle)

        }

        compteExistant.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_inscrireFragment_to_connecterFragment)
        }
    }

    private fun sync() {
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.UNMETERED).build()
        val req = OneTimeWorkRequest.Builder(UtilisateurWorker::class.java).addTag(
            "id1").setConstraints(
            constraints).build()
        val workManager = WorkManager.getInstance()
        workManager.enqueueUniqueWork("work", ExistingWorkPolicy.REPLACE,req)
       // workManager.enqueue(req)
    }


}

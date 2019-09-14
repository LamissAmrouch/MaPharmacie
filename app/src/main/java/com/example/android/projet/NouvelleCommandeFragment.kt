package com.example.android.projet


import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.PermissionChecker.checkSelfPermission
import kotlinx.android.synthetic.main.fragment_nouvelle_commande.*
import android.Manifest
import android.Manifest.permission.*
import android.app.Activity
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.content.*
import android.widget.*
import android.app.AlertDialog
import android.graphics.Bitmap
import android.os.Environment
import android.util.Log
import androidx.core.content.PermissionChecker
import androidx.core.content.contentValuesOf
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.File
import java.io.FileOutputStream
import java.util.*
import android.media.MediaScannerConnection
import android.widget.ArrayAdapter.createFromResource
import androidx.navigation.findNavController
import androidx.work.*
import com.example.android.projet.db_storage.CommandeWorker
import com.example.android.projet.db_storage.RetrofitService
import com.example.android.projet.db_storage.UtilisateurWorker
import com.example.android.projet.entities.Commande
import com.example.android.projet.entities.Pharmacie
import com.example.android.projet.local_storage.RoomService
import kotlinx.android.synthetic.main.content_menu_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat


class NouvelleCommandeFragment : Fragment(), AdapterView.OnItemSelectedListener {


    private val GALLERY = 1
    private val CAMERA = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nouvelle_commande, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /* to get pharmacies list from db */
        val call = RetrofitService.endpoint.getPharmacies()
        call.enqueue(object: Callback<List<Pharmacie>> {
            override fun onResponse(call: Call<List<Pharmacie>>?, response: Response<List<Pharmacie>>?) {
                if(response?.isSuccessful!!) {
                    val list_of_items: ArrayList<String> = ArrayList()
                    val pharmacies = response.body()!!
                    for(pharm in pharmacies ){
                        list_of_items.add(pharm.nom)
                    }
                    val adapter = ArrayAdapter(activity!!, android.R.layout.simple_spinner_item,list_of_items)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner!!.adapter = adapter
                }
                else {
                    Toast.makeText(activity,"fail 2!", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Pharmacie>>?, t: Throwable?) {
                Toast.makeText(activity,t?.message, Toast.LENGTH_SHORT).show()
            }
        })




        photoOrdonnanceBtn.setOnClickListener {
            showPictureDialog()
        }


        EnregistrerBtn.setOnClickListener {
            val d = SimpleDateFormat("dd-MM-yyyy")
            val dateFormat = d.format(Calendar.getInstance().time)
            val nss = Integer.parseInt(arguments?.get("nss").toString())

            val pharmaName= spinner.selectedItem.toString()
            val call1 = RetrofitService.endpoint.getPharmacieByName(pharmaName)
            call1.enqueue(object: Callback<List<Pharmacie>> {
                override fun onResponse(call: Call<List<Pharmacie>>?, response: Response<List<Pharmacie>>?) {
                    if(response?.isSuccessful!!) {
                            val idPharmacie = response.body()?.get(0)?.idP!!
                            //Toast.makeText(activity,"nsss = " +nss+ " id p "+response.body()?.get(0)?.idP!!, Toast.LENGTH_SHORT).show()
                            val cmd = Commande(
                                dateFormat,
                                "photo.jpg",
                                "Reçu",
                                nss,
                                idPharmacie)
                                val call = RetrofitService.endpoint.addCommande(cmd)
                                call.enqueue(object : Callback<String> {
                                override fun onResponse(call: Call<String>?, response: Response<String>?) {
                                    if(response?.isSuccessful!!) {
                                        Toast.makeText(activity,"successful", Toast.LENGTH_SHORT).show()
                                    }
                                }
                                override fun onFailure(call: Call<String>?, t: Throwable?) {
                                    Toast.makeText(activity,t?.message, Toast.LENGTH_SHORT).show()
                                }
                            })


                        //Toast.makeText(activity,"commande sauvgardé !", Toast.LENGTH_SHORT).show()
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

    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(activity!!)
        pictureDialog.setTitle("Sélectionner l'action")
        val pictureDialogItems = arrayOf("Sélectionner photo du Galerie", "Prendre une photo")
        pictureDialog.setItems(pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallary()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    fun choosePhotoFromGallary() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY)
    }

    private fun takePhotoFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY)
        {
            if (data != null)
            {
                val contentURI = data.data
                try
                {
                    val bitmap = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, contentURI)
                    val path = saveImage(bitmap)
                    Toast.makeText(activity,path, Toast.LENGTH_SHORT).show()

                    Toast.makeText(activity!!, "Image enregistrée !", Toast.LENGTH_SHORT).show()
                    imageView2.setImageBitmap(bitmap)

                }
                catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(activity!!, "Erreur !", Toast.LENGTH_SHORT).show()
                }

            }

        }
        else if (requestCode == CAMERA)
        {
            val thumbnail = data!!.extras!!.get("data") as Bitmap
            imageView2.setImageBitmap(thumbnail)
            saveImage(thumbnail)
            Toast.makeText(activity!!, "Image enregistrée!", Toast.LENGTH_SHORT).show()
        }
    }

    fun saveImage(myBitmap: Bitmap):String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val wallpaperDirectory = File(
            (Environment.getExternalStorageDirectory()).toString() + IMAGE_DIRECTORY)
        // have the object build the directory structure, if needed.
        Log.d("fee",wallpaperDirectory.toString())
        if (!wallpaperDirectory.exists()){
            wallpaperDirectory.mkdirs()
        }

        try {
            Log.d("heel",wallpaperDirectory.toString())
            val f = File(wallpaperDirectory, ((Calendar.getInstance()
                .getTimeInMillis()).toString() + ".jpg"))
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(activity!!,
                arrayOf(f.getPath()),
                arrayOf("image/jpeg"), null)
            fo.close()
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath())

            return f.getAbsolutePath()
        }
        catch (e1: IOException) {
            e1.printStackTrace()
        }

        return ""
    }

    companion object {
        private const val IMAGE_DIRECTORY = "/Ordonnances"
    }

    private fun sync() {
        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.UNMETERED).build()
        val req = OneTimeWorkRequest.Builder(CommandeWorker::class.java).addTag(
            "cmdTag"
        ).setConstraints(
            constraints
        ).build()
        val workManager = WorkManager.getInstance()
        workManager.enqueueUniqueWork("work", ExistingWorkPolicy.REPLACE, req)
    }

    // pour le spinner des pharmacies
    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        // use position to know the selected item
    }

    // pour le spinner des pharmacies
    override fun onNothingSelected(p0: AdapterView<*>?) {
        //To change body of created functions use File | Settings | File Templates.
    }

}
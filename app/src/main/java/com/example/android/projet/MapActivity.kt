package com.example.android.projet

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_map.*
import kotlin.String.Companion as String1
import com.google.firebase.auth.AuthResult
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener





class MapActivity : FragmentActivity(), OnMapReadyCallback {

       lateinit var currentLocation : Location
       lateinit var fusedLocation : FusedLocationProviderClient
       private val REQUEST_CODE : Int = 101

       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
           fusedLocation = LocationServices.getFusedLocationProviderClient(this)
           fetchLastLocation()


       }

       private fun fetchLastLocation()
       {
           if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

               ActivityCompat.requestPermissions(
                   this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE )

           }

           val task : Task<Location> = fusedLocation.lastLocation

           task.addOnSuccessListener(object : OnSuccessListener<Location> {
               override fun onSuccess(location: Location?) {

                       if( location != null)
                       {
                           currentLocation = location
                           Toast.makeText(this@MapActivity, "Latitude :" + currentLocation.latitude.toString() + " " +"Longitude :"+ currentLocation!!.longitude.toString(),Toast.LENGTH_LONG).show()
                           val suppormapF : SupportMapFragment = supportFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment
                           suppormapF.getMapAsync(this@MapActivity)
                       }
                   }
           })

        }



       override fun onMapReady(p0: GoogleMap?) {

           var latlong = LatLng(currentLocation.latitude, currentLocation.longitude)
           var mar : MarkerOptions = MarkerOptions().position(latlong)
           p0!!.animateCamera(CameraUpdateFactory.newLatLng(latlong))
           p0.animateCamera(CameraUpdateFactory.newLatLngZoom(latlong, 5F))
           p0.addMarker(mar)
       }




    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if (requestCode == REQUEST_CODE){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                    fetchLastLocation()
            }
        }


    }





}

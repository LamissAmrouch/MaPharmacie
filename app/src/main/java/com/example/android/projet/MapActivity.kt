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
import androidx.annotation.NonNull
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.tasks.OnCompleteListener


class MapActivity : FragmentActivity(), OnMapReadyCallback  , GoogleMap.OnMarkerClickListener {


    private val PHARMACIE1 = LatLng(36.706320, 3.167532)
    private val PHARMACIE2 = LatLng(36.710291, 3.164371)
    private val PHARMACIE3 = LatLng(36.709242, 3.160686)


    private val mPharmacie1: Marker? = null
    private val mPharmacie2: Marker? = null
    private val mPharmacie3: Marker? = null

    private val mMap: GoogleMap? = null

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

        var mMap = p0

        // Add some markers to the map, and add a data object to each marker.
        var mPharmacie1 = mMap.addMarker( MarkerOptions()
            .position(PHARMACIE1)
            .title("Pharmacie Hocine Ali")
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)))
        mPharmacie1.setTag(0);


        var mPharmacie2 = mMap.addMarker( MarkerOptions()
            .position(PHARMACIE2)
            .title("Pharmacie Slimane")
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)))
        mPharmacie2.setTag(0)

        var mPharmacie3 = mMap.addMarker( MarkerOptions()
            .position(PHARMACIE3)
            .title("Pharmacie Benaissa")
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)))
        mPharmacie3.setTag(0)


        // Set a listener for marker click.
        mMap.setOnMarkerClickListener(this)

    }

    override fun onMarkerClick(marker : Marker?): Boolean {


        var clickCount = marker!!.getTag() as Int

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount =  clickCount+1
            marker.setTag(clickCount)
            Toast.makeText(this,
                "Pharmacie de garde :" + marker.getTitle(),
                Toast.LENGTH_SHORT).show()
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;


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
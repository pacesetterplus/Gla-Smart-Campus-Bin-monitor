package com.gla.campus.bin.monitor.views

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.gla.campus.bin.monitor.R
import com.gla.campus.bin.monitor.utils.BinLocations
import com.gla.campus.bin.monitor.utils.Utils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            val success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this, R.raw.style_json
                )
            )
            if (!success) {
                Log.e(Utils.TAG, "Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e(Utils.TAG, "Can't find style. Error: ", e)
        }
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val uofG = LatLng(55.87219, -4.288222)
        val uniCentre = mMap.addMarker(MarkerOptions().position(uofG).title("UofG Main Building"))
        uniCentre.isVisible = false
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(uofG, 15.0F))

        // add markers
        setBinMarker(BinLocations.JWS,"JWS")
        setBinMarker(BinLocations.MAIN_BUILDING,"Main Building")
        setBinMarker(BinLocations.RANKINE,"Rankine")

    }

    fun setBinMarker(location: LatLng,title:String ){
        val markerOption = MarkerOptions().position(location).title(title).icon(generateBitmapDescriptorFromRes(this,
            R.drawable.ic_delete_outline_black_36dp
        ))
        mMap.addMarker(markerOption)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mapTypeNormal -> {
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            }
            R.id.mapTypeHybrid -> {
                mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            }
            R.id.mapTypeSatellite -> {
                mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            }
            R.id.mapTypeTerrain -> {
                mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }
            else -> return false
        }
        return false
    }

    fun generateBitmapDescriptorFromRes(context: Context, resId: Int): BitmapDescriptor {
        val drawable = ContextCompat.getDrawable(context, resId)
        drawable!!.setBounds(
            0,
            0,
            drawable.intrinsicWidth,
            drawable.intrinsicHeight
        )
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

}

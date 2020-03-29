package com.gla.campus.bin.monitor.views

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.gla.campus.bin.monitor.R
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

/**
 * A simple [Fragment] subclass.
 */
class CustomMapFragment : SupportMapFragment() {

    private lateinit var mActivity: OnMapReadyCallback

    override fun onActivityCreated(bundle: Bundle?) {
        super.onActivityCreated(bundle)
        setHasOptionsMenu(true)
        mActivity =  activity as OnMapReadyCallback
        getMapAsync(mActivity)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_map,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }



}

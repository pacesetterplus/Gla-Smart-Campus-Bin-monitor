package com.gla.campus.bin.monitor.model

import com.google.android.gms.maps.model.LatLng

data class SmartBin (val id: Int,val latLng: LatLng,val locationName: String, var level: Double ){
}
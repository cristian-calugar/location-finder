package com.rebeldot.locationfinder.domain.api

import android.content.Context
import android.location.Geocoder
import java.util.*

object LocationServiceFactory {

    fun getLocationService(context: Context): LocationService {
        val geocoder = getGeocoder(context)

        return LocationService(geocoder)
    }

    private fun getGeocoder(context: Context): Geocoder {
        return Geocoder(context, Locale.getDefault())
    }
}

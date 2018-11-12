package com.rebeldot.locationfinder.domain.api

import android.location.Address
import android.location.Geocoder

class LocationService(
    private val geocoder: Geocoder
) {

    companion object {
        private const val MAX_RESULTS = 20
    }

    fun getAddressesByLocationName(locationName: String): List<Address> {
        return geocoder.getFromLocationName(locationName, MAX_RESULTS)
    }
}

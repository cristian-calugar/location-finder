package com.rebeldot.locationfinder.domain

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.text.TextUtils
import com.rebeldot.locationfinder.domain.model.Location
import java.util.*

object LocationFinder {

    fun getLocationsByLocationName(context: Context, locationName: String): List<Location> {
        val locations = ArrayList<Location>()

        if (TextUtils.isEmpty(locationName)) {
            return locations
        }

        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocationName(locationName, 5)

        if (addresses != null) {
            for (address in addresses) {
                val location = mapAddressToLocation(address)
                locations.add(location)
            }
        }

        return locations
    }

    private fun mapAddressToLocation(address: Address): Location {
        val locationAddress = getLocationAddress(address)
        return Location(address.featureName, locationAddress, address.latitude, address.longitude)
    }

    private fun getLocationAddress(address: Address): String {
        val locationAddressBuilder = StringBuilder()

        for (i in 0..address.maxAddressLineIndex) {
            locationAddressBuilder.append(address.getAddressLine(i))

            if (i < address.maxAddressLineIndex) {
                locationAddressBuilder.append(", ")
            }
        }

        return locationAddressBuilder.toString()
    }

}

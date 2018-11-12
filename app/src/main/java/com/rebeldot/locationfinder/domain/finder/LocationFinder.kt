package com.rebeldot.locationfinder.domain.finder

import android.text.TextUtils
import com.rebeldot.locationfinder.domain.api.LocationService
import com.rebeldot.locationfinder.domain.model.Location
import java.util.*

class LocationFinder(
    private val locationService: LocationService,
    private val locationMapper: LocationMapper
) {

    fun getLocationsByLocationName(locationName: String): List<Location> {
        if (TextUtils.isEmpty(locationName)) {
            return ArrayList()
        }

        val addresses = locationService.getAddressesByLocationName(locationName)

        return locationMapper.mapAddressesToLocations(addresses)
    }
}

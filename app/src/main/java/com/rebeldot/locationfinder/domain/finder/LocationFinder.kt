package com.rebeldot.locationfinder.domain.finder

import com.rebeldot.locationfinder.domain.api.LocationService
import com.rebeldot.locationfinder.domain.model.Location
import java.io.IOException
import java.util.*

class LocationFinder(
    private val locationService: LocationService,
    private val locationMapper: LocationMapper
) {

    fun getLocationsByLocationName(locationName: String): List<Location> {
        if (locationName.isEmpty()) {
            return ArrayList()
        }

        return try {
            val addresses = locationService.getAddressesByLocationName(locationName)

            locationMapper.mapAddressesToLocations(addresses)
        } catch (e: IOException) {
            ArrayList()
        }
    }
}

package com.rebeldot.locationfinder.domain.finder

import com.rebeldot.locationfinder.domain.api.LocationService
import com.rebeldot.locationfinder.domain.model.Location
import java.util.*

class LocationFinder(
    private val locationService: LocationService,
    private val locationMapper: LocationMapper
) {

    fun getLocationsByLocationName(locationName: String): List<Location> {
        if (locationName.isEmpty()) {
            return ArrayList()
        }

        val addresses = locationService.getAddressesByLocationName(locationName)

        return locationMapper.mapAddressesToLocations(addresses)
    }
}

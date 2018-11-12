package com.rebeldot.locationfinder.domain.finder

import android.content.Context
import com.rebeldot.locationfinder.domain.api.LocationService
import com.rebeldot.locationfinder.domain.api.LocationServiceFactory

object LocationFinderFactory {

    fun getLocationFinder(context: Context): LocationFinder {
        val locationService = getLocationService(context)
        val locationMapper = getLocationMapper()

        return LocationFinder(locationService, locationMapper)
    }

    private fun getLocationService(context: Context): LocationService {
        return LocationServiceFactory.getLocationService(context)
    }

    private fun getLocationMapper(): LocationMapper {
        return LocationMapper()
    }
}

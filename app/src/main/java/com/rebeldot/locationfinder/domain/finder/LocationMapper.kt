package com.rebeldot.locationfinder.domain.finder

import android.location.Address
import com.rebeldot.locationfinder.domain.model.Location

class LocationMapper {

    fun mapAddressesToLocations(addresses: List<Address>): List<Location> {
        return addresses.map { mapAddressToLocation(it) }
    }

    private fun mapAddressToLocation(address: Address): Location {
        val locationAddress = buildLocationAddress(address)

        return Location(address.featureName, locationAddress, address.latitude, address.longitude)
    }

    private fun buildLocationAddress(address: Address): String {
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

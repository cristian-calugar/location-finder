package com.rebeldot.locationfinder.domain

import android.location.Address
import com.rebeldot.locationfinder.domain.model.Location
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

object LocationTestsHelper {

    const val LOCATION_NAME = "A location name"
    private const val LOCATION_ADDRESS = "First address line, Second address line"
    private const val LOCATION_LATITUDE = 46.77
    private const val LOCATION_LONGITUDE = 23.62

    private const val MAX_ADDRESS_LINE_INDEX = 1
    private const val FIRST_ADDRESS_LINE = "First address line"
    private const val SECOND_ADDRESS_LINE = "Second address line"

    fun buildAddressesListWithOneElement(): List<Address> {
        return listOf(buildAddress())
    }

    private fun buildAddress(): Address {
        val addressMock = mock(Address::class.java)

        `when`(addressMock.featureName).thenReturn(LOCATION_NAME)
        `when`(addressMock.latitude).thenReturn(LOCATION_LATITUDE)
        `when`(addressMock.longitude).thenReturn(LOCATION_LONGITUDE)
        `when`(addressMock.maxAddressLineIndex).thenReturn(MAX_ADDRESS_LINE_INDEX)
        `when`(addressMock.getAddressLine(0)).thenReturn(FIRST_ADDRESS_LINE)
        `when`(addressMock.getAddressLine(1)).thenReturn(SECOND_ADDRESS_LINE)

        return addressMock
    }

    fun buildLocationsListWithOneElement(): List<Location> {
        return listOf(buildLocation())
    }

    private fun buildLocation(): Location {
        return Location(
            LOCATION_NAME,
            LOCATION_ADDRESS,
            LOCATION_LATITUDE,
            LOCATION_LONGITUDE
        )
    }
}

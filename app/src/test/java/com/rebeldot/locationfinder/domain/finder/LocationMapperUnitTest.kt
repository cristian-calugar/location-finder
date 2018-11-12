package com.rebeldot.locationfinder.domain.finder

import com.rebeldot.locationfinder.domain.LocationTestsHelper
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class LocationMapperUnitTest {

    private lateinit var locationMapper: LocationMapper

    @Before
    fun setUp() {
        locationMapper = LocationMapper()
    }

    @Test
    fun mapOneAddressToLocationTest() {
        // Given
        val givenAddresses = LocationTestsHelper.buildAddressesListWithOneElement()

        // When
        val locations = locationMapper.mapAddressesToLocations(givenAddresses)

        // Then
        val expectedLocations = LocationTestsHelper.buildLocationsListWithOneElement()

        assertEquals(expectedLocations, locations)
    }

    @Test
    fun mapEmptyListOfAddressesTest() {
        fail("Not implemented yet!")
    }

    @Test
    fun mapMoreAddressesToLocationsTest() {
        fail("Not implemented yet!")
    }
}

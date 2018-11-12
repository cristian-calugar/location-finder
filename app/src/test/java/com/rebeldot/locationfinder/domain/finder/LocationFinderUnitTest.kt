package com.rebeldot.locationfinder.domain.finder

import com.rebeldot.locationfinder.domain.LocationTestsHelper
import com.rebeldot.locationfinder.domain.api.LocationService
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verifyZeroInteractions
import org.mockito.MockitoAnnotations

class LocationFinderUnitTest {

    companion object {
        private const val EMPTY_LOCATION_NAME = ""
    }

    @Mock
    private lateinit var locationServiceMock: LocationService

    @Mock
    private lateinit var locationMapperMock: LocationMapper

    @InjectMocks
    private lateinit var locationFinder: LocationFinder

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getLocationsByEmptyLocationNameTest() {
        // When
        val locations = locationFinder.getLocationsByLocationName(EMPTY_LOCATION_NAME)

        // Then
        assertEquals(0, locations.size)

        verifyZeroInteractions(locationServiceMock, locationMapperMock)
    }

    @Test
    fun getLocationsByValidLocationNameTest() {
        // Given
        val addresses = LocationTestsHelper.buildAddressesListWithOneElement()

        `when`(locationServiceMock.getAddressesByLocationName(LocationTestsHelper.LOCATION_NAME))
            .thenReturn(addresses)

        val expectedLocations = LocationTestsHelper.buildLocationsListWithOneElement()

        `when`(locationMapperMock.mapAddressesToLocations(addresses))
            .thenReturn(expectedLocations)

        // When
        val locations = locationFinder.getLocationsByLocationName(LocationTestsHelper.LOCATION_NAME)

        // Then
        assertEquals(expectedLocations, locations)
    }
}

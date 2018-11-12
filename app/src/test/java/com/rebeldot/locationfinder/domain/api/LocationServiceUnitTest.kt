package com.rebeldot.locationfinder.domain.api

import android.location.Geocoder
import com.rebeldot.locationfinder.domain.LocationTestsHelper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LocationServiceUnitTest {

    companion object {
        private const val MAX_RESULTS = 20
    }

    @Mock
    private lateinit var geocoderMock: Geocoder

    @InjectMocks
    private lateinit var locationService: LocationService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getAddressesByLocationNameTest() {
        // Given
        val expectedAddresses = LocationTestsHelper.buildAddressesListWithOneElement()

        `when`(geocoderMock.getFromLocationName(LocationTestsHelper.LOCATION_NAME, MAX_RESULTS))
            .thenReturn(expectedAddresses)

        // When
        val addresses = locationService.getAddressesByLocationName(LocationTestsHelper.LOCATION_NAME)

        // Then
        assertEquals(expectedAddresses, addresses)
    }
}

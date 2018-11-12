package com.rebeldot.locationfinder.domain.finder

import com.rebeldot.locationfinder.domain.api.LocationService
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
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
        val locations = locationFinder.getLocationsByLocationName(EMPTY_LOCATION_NAME)

        assertEquals(0, locations.size)

        verifyZeroInteractions(locationServiceMock, locationMapperMock)
    }

}
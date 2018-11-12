package com.rebeldot.locationfinder.domain.model

import java.io.Serializable

class Location(
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double
) : Serializable

package com.rebeldot.locationfinder.features.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rebeldot.locationfinder.R
import com.rebeldot.locationfinder.domain.model.Location
import kotlinx.android.synthetic.main.activity_location_details.*

class LocationDetailsActivity : AppCompatActivity() {

    companion object {
        const val LOCATION_PARAMETER = "location"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_details)

        setupActionBar()

        retrieveAndDisplayLocation()
    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun retrieveAndDisplayLocation() {
        val location = intent.getSerializableExtra(LOCATION_PARAMETER) as? Location

        displayLocation(location)
    }

    private fun displayLocation(location: Location?) {
        if (location == null) return

        location_name.text = location.name
        location_address.text = location.address
        location_latitude.text = location.latitude.toString()
        location_longitude.text = location.longitude.toString()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}

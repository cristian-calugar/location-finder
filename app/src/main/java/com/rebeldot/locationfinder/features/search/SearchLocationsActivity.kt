package com.rebeldot.locationfinder.features.search

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.SearchView
import com.rebeldot.locationfinder.R
import com.rebeldot.locationfinder.domain.finder.LocationFinderFactory
import com.rebeldot.locationfinder.domain.model.Location
import com.rebeldot.locationfinder.features.details.LocationDetailsActivity
import kotlinx.android.synthetic.main.activity_search_locations.*
import java.lang.ref.WeakReference
import java.util.*

class SearchLocationsActivity : AppCompatActivity(), LocationsAdapter.LocationSelectionListener {

    private lateinit var locationsAdapter: LocationsAdapter

    private var locationFinder = LocationFinderFactory.getLocationFinder(this)

    private val onQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String): Boolean {
            SearchLocationsAsyncTask(
                this@SearchLocationsActivity,
                newText
            ).execute()
            return false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_locations)

        setupSearchView()
        setupLocationsView()
    }

    private fun setupSearchView() {
        search_view.setOnQueryTextListener(onQueryTextListener)
    }

    private fun setupLocationsView() {
        locations_recycler.layoutManager = LinearLayoutManager(this)
        locations_recycler.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        locationsAdapter = LocationsAdapter(this)
        locations_recycler.adapter = locationsAdapter

    }

    override fun onLocationSelected(location: Location) {
        launchLocationDetailsScreen(location)
    }

    private fun launchLocationDetailsScreen(location: Location) {
        val intent = Intent(this, LocationDetailsActivity::class.java)

        intent.putExtra(LocationDetailsActivity.LOCATION_PARAMETER, location)

        startActivity(intent)
    }

    private fun searchLocationsByName(searchText: String): List<Location> {
        return locationFinder.getLocationsByLocationName(searchText)
    }

    private fun displayLocations(locations: List<Location>) {
        locationsAdapter.locations = locations
        locationsAdapter.notifyDataSetChanged()
    }

    private class SearchLocationsAsyncTask internal constructor(
        context: SearchLocationsActivity,
        private val searchText: String
    ) : AsyncTask<Void, Void, List<Location>>() {

        private val activityReference: WeakReference<SearchLocationsActivity> = WeakReference(context)

        override fun doInBackground(vararg voids: Void): List<Location> {
            val activity = activityReference.get()
            if (activity == null || activity.isFinishing) return ArrayList()

            return activity.searchLocationsByName(searchText)
        }

        override fun onPostExecute(locations: List<Location>) {
            val activity = activityReference.get()
            if (activity == null || activity.isFinishing) return

            activity.displayLocations(locations)
        }
    }
}

package com.rebeldot.locationfinder.features.search

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.SearchView
import com.rebeldot.locationfinder.R
import com.rebeldot.locationfinder.domain.finder.LocationFinder
import com.rebeldot.locationfinder.domain.finder.LocationFinderFactory
import com.rebeldot.locationfinder.domain.model.Location
import com.rebeldot.locationfinder.features.details.LocationDetailsActivity
import kotlinx.android.synthetic.main.activity_search_location.*
import java.lang.ref.WeakReference
import java.util.*

class SearchLocationActivity : AppCompatActivity(), LocationsAdapter.LocationSelectionListener {

    private lateinit var locationsAdapter: LocationsAdapter

    private val locationFinder: LocationFinder by lazy { LocationFinderFactory.getLocationFinder(this) }

    private val onQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            enqueueLocationSearchTask(query)

            return false
        }

        override fun onQueryTextChange(newText: String): Boolean {
            enqueueLocationSearchTask(newText)

            return false
        }
    }

    private fun enqueueLocationSearchTask(query: String) {
        SearchLocationAsyncTask(this, query).execute()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_location)

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

    private fun showProgressIndicator() {
        progress_indicator.show()
    }

    private fun hideProgressIndicator(searchText: String) {
        if (search_view.query.toString() == searchText) {
            progress_indicator.hide()
        }
    }

    private fun searchLocationByName(searchText: String): List<Location> {
        return locationFinder.getLocationsByLocationName(searchText)
    }

    private fun displayLocations(locations: List<Location>) {
        locationsAdapter.locations = locations
        locationsAdapter.notifyDataSetChanged()
    }

    private class SearchLocationAsyncTask internal constructor(
        context: SearchLocationActivity,
        private val searchText: String
    ) : AsyncTask<Void, Void, List<Location>>() {

        private val activityReference: WeakReference<SearchLocationActivity> = WeakReference(context)

        override fun onPreExecute() {
            val activity = activityReference.get()
            if (activity == null || activity.isFinishing) return

            activity.showProgressIndicator()
        }

        override fun doInBackground(vararg voids: Void): List<Location> {
            val activity = activityReference.get()
            if (activity == null || activity.isFinishing) return ArrayList()

            return activity.searchLocationByName(searchText)
        }

        override fun onPostExecute(locations: List<Location>) {
            val activity = activityReference.get()
            if (activity == null || activity.isFinishing) return

            activity.displayLocations(locations)
            activity.hideProgressIndicator(searchText)
        }
    }
}

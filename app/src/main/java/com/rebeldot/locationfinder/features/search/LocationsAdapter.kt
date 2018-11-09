package com.rebeldot.locationfinder.features.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rebeldot.locationfinder.R
import com.rebeldot.locationfinder.domain.model.Location

class LocationsAdapter(
    private val locationSelectionListener: LocationSelectionListener
) : RecyclerView.Adapter<LocationsAdapter.ViewHolder>() {

    var locations: List<Location> = arrayListOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val location = locations[position]

        holder.locationName.text = location.name
        holder.locationAddress.text = location.address

        holder.locationRow.setOnClickListener {
            locationSelectionListener.onLocationSelected(location)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_location, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val locationRow: View = view.findViewById(R.id.location_row)
        val locationName: TextView = view.findViewById(R.id.location_name)
        val locationAddress: TextView = view.findViewById(R.id.location_address)
    }

    interface LocationSelectionListener {
        fun onLocationSelected(location: Location)
    }
}

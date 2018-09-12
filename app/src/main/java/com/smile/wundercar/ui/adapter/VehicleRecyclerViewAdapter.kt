package com.smile.wundercar.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.smile.wundercar.R
import com.smile.wundercar.model.Vehicle
import kotlinx.android.synthetic.main.fragment_vehicle.view.*

class VehicleRecyclerViewAdapter(
        private val mValues: List<Vehicle>)
    : RecyclerView.Adapter<VehicleRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_vehicle, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        with(holder) {
            title.text = item.name
            address.text = item.address
            engineType.text = item.engineType
            vin.text = item.vin
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val title: TextView = mView.itemTitle
        val address: TextView = mView.address
        val engineType: TextView = mView.engineType
        val vin: TextView = mView.vin
    }
}

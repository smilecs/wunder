package com.smile.wundercar.ui.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.past3.ketro.api.Kobserver
import com.smile.wundercar.R
import com.smile.wundercar.model.Vehicle
import com.smile.wundercar.ui.VehicleViewModel
import kotlinx.android.synthetic.main.fragment_vehicle_list.*


class VehicleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_vehicle_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getVehicles()
    }

    private fun getVehicles() {
        val viewModel = ViewModelProviders.of(requireActivity()).get(VehicleViewModel::class.java)
        viewModel.getVehicles().observe(this, object : Kobserver<List<Vehicle>>() {
            override fun onException(exception: Exception) {
                val message = exception.message ?: getString(R.string.generic_error_message)
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }

            override fun onSuccess(data: List<Vehicle>) {
                initList(data)
            }
        })
    }

    private fun initList(data: List<Vehicle>) {
        with(list) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
            adapter = VehicleRecyclerViewAdapter(data)
        }
    }
}


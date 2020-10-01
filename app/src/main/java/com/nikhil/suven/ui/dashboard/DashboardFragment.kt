package com.nikhil.suven.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nikhil.suven.R
import com.nikhil.suven.databinding.FragmentDashboardBinding
import com.nikhil.suven.ui.dashboard.adapter.PurchaseRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private val dashboardViewModel: DashboardViewModel by viewModels()
    private lateinit var binding: FragmentDashboardBinding
    private val adapter = PurchaseRecyclerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardBinding.bind(view)
        binding.vm = dashboardViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setUpListAdapter()
    }

    private fun setUpListAdapter() {
        binding.purchasesRecyclerView.adapter = adapter
        dashboardViewModel.list.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            if(it.isNotEmpty()) {
                binding.noDataImageView.visibility = View.GONE
                binding.noUnitsTextView.visibility = View.GONE
                binding.purchaseMessageTextView.visibility = View.GONE
            }
        })
    }
}
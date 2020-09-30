package com.nikhil.suven.ui.goals

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.nikhil.suven.R
import com.nikhil.suven.databinding.FragmentGoalsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GoalsFragment : Fragment(R.layout.fragment_goals) {

    private lateinit var binding: FragmentGoalsBinding
    private val goalsViewModel: GoalsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGoalsBinding.bind(view)
        binding.viewModel = goalsViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.purchaseTextView.setOnClickListener {
            this.activity?.supportFragmentManager?.let { supportFragmentManager ->
                DatePickerFragment().show(supportFragmentManager, "DatePicker")
            }
        }
    }

}
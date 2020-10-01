package com.nikhil.suven.ui.goals

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import com.nikhil.suven.R
import com.nikhil.suven.databinding.FragmentGoalsBinding
import com.nikhil.suven.utils.setupSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class GoalsFragment : Fragment(R.layout.fragment_goals), AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentGoalsBinding
    private val goalsViewModel: GoalsViewModel by activityViewModels()
    private val forWhoArray: Array<String> by lazy {
        val ac = requireNotNull(activity)
        ac.resources.getStringArray(R.array.for_who)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGoalsBinding.bind(view)


        binding.apply {
            viewModel = goalsViewModel
            lifecycleOwner = viewLifecycleOwner

            val spinnerAdapter = ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                forWhoArray
            )
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            with(whoSpinner) {
                adapter = spinnerAdapter
                setSelection(goalsViewModel.forWhom, false)
                onItemSelectedListener = this@GoalsFragment
            }

            purchaseTextView.setOnClickListener {
                activity?.supportFragmentManager?.let { supportFragmentManager ->
                    DatePickerFragment().show(supportFragmentManager, "DatePicker")
                }
            }

            isFixedSwitchCompact.setOnCheckedChangeListener{buttonView, isChecked ->
                goalsViewModel.isVariable.postValue(isChecked)
            }
        }
        setupSnackbar()
    }

    override fun onPause() {
        super.onPause()
        /*goalsViewModel.apply {
            setNumberOfUnits(binding.unitsEdiText.text.toString())
            setPurchasePrice(binding.purchasePriceEdiText.text.toString())
        }*/
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        goalsViewModel.forWhom = position
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // do nothing
    }

    private fun setupSnackbar() {
        view?.setupSnackbar(this, goalsViewModel.snackbarText, Snackbar.LENGTH_SHORT)
    }

}
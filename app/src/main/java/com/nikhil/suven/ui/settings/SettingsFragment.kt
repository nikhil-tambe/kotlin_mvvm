package com.nikhil.suven.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.nikhil.suven.R
import com.nikhil.suven.databinding.FragmentSettingsBinding
import com.nikhil.suven.ui.add_purchase.AddPurchaseFragment
import com.nikhil.suven.ui.your_family.YourFamilyFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings), TabLayout.OnTabSelectedListener {

    private lateinit var binding: FragmentSettingsBinding
    private val settingsViewModel: SettingsViewModel by viewModels()
    private val tabTitles = arrayOf(
        "Your Info",
        "Know You\nBetter",
        "Know Your\nRisk",
        "Your\nFamily"
    )
    private val imageResId = intArrayOf(
        R.drawable.ic_round_person_add_24,
        R.drawable.ic_round_person_pin_24,
        R.drawable.ic_round_bug_report_24,
        R.drawable.ic_round_people_24
    )
    private val selectedColor: Int by lazy {
        ContextCompat.getColor(requireContext(), R.color.colorAccent)
    }
    private val defaultColor: Int by lazy {
        ContextCompat.getColor(requireContext(), R.color.colorPagerArrowBackground)
    }

    companion object{
        fun newInstance(): SettingsFragment {
            val args = Bundle()
            val fragment = SettingsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)

        binding.pager.isSaveEnabled = false
        binding.pager.adapter = DemoCollectionAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.customView = getTabView(position)
            tab.customView?.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
            /** If not done, causes all tabs to appear with 'selectedColor' on using
             *  BottomNavigationView or on Rotating Screen */
            tab.customView?.background?.setTint(defaultColor)
        }.attach()
    }

    override fun onResume() {
        super.onResume()
        binding.tabLayout.addOnTabSelectedListener(this)
    }

    override fun onPause() {
        super.onPause()
        binding.tabLayout.removeOnTabSelectedListener(this)
    }

    private fun getTabView(position: Int): View? {
        val v: View = LayoutInflater.from(context).inflate(R.layout.view_tab_custom, null)
        val text1 = v.findViewById<View>(R.id.text1) as TextView
        text1.text = tabTitles[position]
        val icon: ImageView = v.findViewById<View>(R.id.icon) as ImageView
        icon.setImageResource(imageResId[position])
        return v
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        tab?.customView?.background?.setTint(selectedColor)
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        tab?.customView?.background?.setTint(defaultColor)
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        // Do Nothing
    }
}

@ExperimentalCoroutinesApi
class DemoCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AddPurchaseFragment()
            //1 -> KnowYou()
            //2 -> KnowYourRisk()
            else -> YourFamilyFragment()
        }
    }


}
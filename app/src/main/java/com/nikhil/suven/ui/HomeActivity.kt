package com.nikhil.suven.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.nikhil.magnifiedbottombar.MagnifiedBottomBar
import com.nikhil.suven.R
import com.nikhil.suven.databinding.ActivityHomeBinding
import com.nikhil.suven.ui.add_purchase.AddPurchaseFragment
import com.nikhil.suven.ui.chat.ChatFragment
import com.nikhil.suven.ui.dashboard.DashboardFragment
import com.nikhil.suven.ui.settings.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    private val dashboardFragment: DashboardFragment by lazy { DashboardFragment.newInstance() }
    private val settingsFragment: SettingsFragment by lazy { SettingsFragment.newInstance() }
    private val chatFragment: ChatFragment by lazy { ChatFragment.newInstance() }

    companion object {
        private const val ID_DASHBOARD = 1
        private const val ID_ADD = 2
        private const val ID_CHAT = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        binding.magnifiedBottomBar.apply {
            add(MagnifiedBottomBar.Model(ID_DASHBOARD, R.drawable.ic_round_dashboard_24))
            add(MagnifiedBottomBar.Model(ID_ADD, R.drawable.ic_round_settings_24))
            add(MagnifiedBottomBar.Model(ID_CHAT, R.drawable.ic_round_message_24))

            setOnShowListener {
                when (it.id) {
                    ID_DASHBOARD -> showDashboardFragment()
                    ID_CHAT -> showChatFragment()
                    else -> showAddPurchaseFragment()
                }
            }

            setOnReselectListener {
                val s = "item ${it.id} is reselected."
                Timber.d(s)
            }

            show(ID_ADD)
        }
    }

    private fun showDashboardFragment() = supportFragmentManager.beginTransaction()
        .replace(binding.containerFrameLayout.id, dashboardFragment)
        .commit()

    private fun showAddPurchaseFragment() = supportFragmentManager.beginTransaction()
        .replace(binding.containerFrameLayout.id, settingsFragment)
        .commit()

    private fun showChatFragment() = supportFragmentManager.beginTransaction()
        .replace(binding.containerFrameLayout.id, chatFragment)
        .commit()

}
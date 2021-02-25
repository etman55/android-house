package com.atef.clubhouse.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.atef.clubhouse.R
import com.atef.clubhouse.databinding.ActivityMainBinding
import com.atef.clubhouse.domain.entity.auth.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.apply {
            observeNavigation(this@MainActivity, ::handleNavigation)
            user.observe(this@MainActivity, ::handleUserPersistence)
            warning.observe(this@MainActivity, ::handleWarning)
        }
    }

    private fun handleNavigation(navigation: MainNavigation) {
        when (navigation) {
            MainNavigation.Login -> findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_auth_graph)
            MainNavigation.Waiting -> findNavController(R.id.nav_host_fragment).navigate(R.id.homeFragment)
            MainNavigation.Home -> findNavController(R.id.nav_host_fragment).navigate(R.id.homeFragment)
        }
    }

    private fun handleUserPersistence(user: User?) {
        user?.let {
            user.isWaitListed?.let { if (it) viewModel.navigateToHome() else viewModel.navigateToWaiting() }
        } ?: run {
            viewModel.navigateToLogin()
        }
    }

    private fun handleWarning(show: Boolean) {
        if (show) {
            AlertDialog.Builder(this@MainActivity)
                .setTitle(R.string.warning)
                .setMessage(R.string.warning_text)
                .setPositiveButton(R.string.i_understand, null)
                .setCancelable(false)
                .show()
            viewModel.setShowWarning(false)
        }
    }

}

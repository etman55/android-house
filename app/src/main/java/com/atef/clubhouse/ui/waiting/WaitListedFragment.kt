package com.atef.clubhouse.ui.waiting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.atef.clubhouse.R
import com.atef.clubhouse.base.extension.viewBinding
import com.atef.clubhouse.databinding.FragmentWaitListedBinding
import com.atef.clubhouse.domain.entity.auth.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WaitListedFragment : Fragment(R.layout.fragment_wait_listed) {
    private val viewModel: WaitedListViewModel by viewModels()
    private val binding: FragmentWaitListedBinding by viewBinding(FragmentWaitListedBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            observeNavigation(viewLifecycleOwner, ::handleNavigation)
            user.observe(viewLifecycleOwner, ::handleWaitListed)
        }
        binding.logoutBtn.setOnClickListener {
            viewModel.navigateToLogout()
        }
    }

    private fun handleNavigation(navigation: WaitListedNavigation) {
        when (navigation) {
            WaitListedNavigation.Home -> findNavController().navigate(WaitListedFragmentDirections.actionWaitListedFragmentToHomeFragment())
            WaitListedNavigation.Logout -> findNavController().navigate(R.id.action_global_auth_graph)
        }
    }

    private fun handleWaitListed(user: User?) {
        user?.let {
            if (!user.isWaitListed!!)
                viewModel.navigateToHome()
        }
    }
}
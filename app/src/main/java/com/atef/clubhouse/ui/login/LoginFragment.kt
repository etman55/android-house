package com.atef.clubhouse.ui.login

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.atef.clubhouse.R
import com.atef.clubhouse.base.Resource
import com.atef.clubhouse.base.Status
import com.atef.clubhouse.base.extension.viewBinding
import com.atef.clubhouse.data.remote.base.model.BaseResponse
import com.atef.clubhouse.databinding.FragmentLoginBinding
import com.atef.clubhouse.domain.entity.country.Country
import com.atef.clubhouse.ui.country.CountryViewModel
import com.atef.clubhouse.utils.NetworkStateDialog
import com.atef.clubhouse.utils.snack
import com.atef.clubhouse.utils.textAsBitmap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val viewModel: LoginViewModel by viewModels()
    private val countryViewModel: CountryViewModel by navGraphViewModels(R.id.auth_graph) { defaultViewModelProviderFactory }

    private val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            observeNavigation(viewLifecycleOwner, ::handleNavigation)
            currentCountry.observe(viewLifecycleOwner, ::handleSelectedCountry)
            startPhoneNumber.observe(viewLifecycleOwner, ::handleStartPhoneAuth)
        }
        countryViewModel.selectedCountry.observe(viewLifecycleOwner, ::handleSelectedCountry)
        binding.countryCodeBtn.setOnClickListener {
            findNavController().navigate(R.id.countryPickerFragment)
        }
        binding.nextBtn.setOnClickListener {
            viewModel.startPhoneNumberAuth("${binding.countryCodeBtn.text}${binding.phoneNumberTxt.editableText.trim()}")
        }
    }

    private fun handleNavigation(navigation: LoginNavigation) {
        if (navigation is LoginNavigation.VerificationCodeNavigation)
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToVerificationCodeFragment(navigation.phoneNumber)
            )
    }

    private fun handleStartPhoneAuth(resource: Resource<BaseResponse>) {
        when (resource.status) {
            Status.LOADING -> NetworkStateDialog.show(requireContext())
            Status.SUCCESS -> {
                NetworkStateDialog.dismiss()
                viewModel.navigateToVerificationCode("${binding.countryCodeBtn.text}${binding.phoneNumberTxt.editableText.trim()}")
            }
            Status.ERROR -> {
                NetworkStateDialog.dismiss()
                resource.message?.let { binding.countryCodeBtn.snack(it) }
                resource.messageRes?.let { binding.countryCodeBtn.snack(it) }
            }
        }
    }

    private fun handleSelectedCountry(country: Country) {
        binding.countryCodeBtn.icon =
            BitmapDrawable(binding.root.context.resources, country.emojii.textAsBitmap(binding.root.context))
        binding.countryCodeBtn.text = country.dialCode
    }

}
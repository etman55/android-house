package com.atef.clubhouse.ui.verificationcode

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.atef.clubhouse.R
import com.atef.clubhouse.base.Resource
import com.atef.clubhouse.base.Status
import com.atef.clubhouse.base.extension.viewBinding
import com.atef.clubhouse.base.handleResource
import com.atef.clubhouse.data.remote.feature.auth.model.CompletePhoneNumberAuthResponse
import com.atef.clubhouse.databinding.FragmentVerificationCodeBinding
import com.atef.clubhouse.utils.NetworkStateDialog
import com.atef.clubhouse.utils.snack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerificationCodeFragment : Fragment(R.layout.fragment_verification_code) {
    private val viewModel: VerificationCodeViewModel by viewModels()
    private val binding: FragmentVerificationCodeBinding by viewBinding(FragmentVerificationCodeBinding::bind)
    private val args: VerificationCodeFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            observeNavigation(viewLifecycleOwner, ::handleNavigation)
            completePhoneAuth.observe(viewLifecycleOwner, ::handleCompleteAuth)
            resendVerificationCode.observe(viewLifecycleOwner, ::handleResendVerificationCode)
        }
        binding.apply {
            nextBtn.setOnClickListener {
                viewModel.completeAuth(args.phoneNumber, verificationCodeInput.editableText.trim().toString())
            }
            resendCodeBtn.setOnClickListener {
                viewModel.resendCode(args.phoneNumber)
            }
            backBtn.setOnClickListener {
                viewModel.navigationBack()
            }
        }
    }

    private fun handleResendVerificationCode(resource: Resource<Unit>) {
        resource.handleResource(requireContext(),
            onError = { msg: String?, msgRes: Int? ->
                msgRes?.let { binding.resendCodeBtn.snack(it) }
                msg?.let { binding.resendCodeBtn.snack(it) }
            })
    }

    private fun handleCompleteAuth(resource: Resource<CompletePhoneNumberAuthResponse>) {
        resource.handleResource(requireContext(),
            onSuccess = { response ->
                response?.isWaitListed?.let {
                    if (it) viewModel.navigateToWaitListed() else viewModel.navigateHome()
                }
            }, onError = { msg, msgRes ->
                msgRes?.let { binding.resendCodeBtn.snack(it) }
                msg?.let { binding.resendCodeBtn.snack(it) }
            })
    }

    private fun handleNavigation(navigation: VerificationCodeNavigation) {
        when (navigation) {
            VerificationCodeNavigation.Back -> findNavController().navigateUp()
            VerificationCodeNavigation.Home -> findNavController().navigate(R.id.homeFragment)
            VerificationCodeNavigation.WaitListed -> findNavController().navigate(R.id.waitListedFragment)
        }
    }
}
package com.atef.clubhouse.ui.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.atef.clubhouse.R
import com.atef.clubhouse.base.extension.viewBinding
import com.atef.clubhouse.databinding.FragmentCountryPickerBinding
import com.atef.clubhouse.domain.entity.country.Country
import com.atef.clubhouse.utils.ImageLoader
import com.atef.clubhouse.utils.doAfterSearchQueryChange
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CountryPickerFragment : BottomSheetDialogFragment() {
    private val viewModel: CountryViewModel by navGraphViewModels(R.id.auth_graph)

    @Inject
    lateinit var imageLoader: ImageLoader
    private var countriesAdapter: CountriesAdapter? = null

    private val binding: FragmentCountryPickerBinding by viewBinding(FragmentCountryPickerBinding::bind)

    override fun onStart() {
        super.onStart()
        dialog?.let {
            val bottomSheet: View = dialog!!.findViewById(R.id.design_bottom_sheet)
            bottomSheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_country_picker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (dialog as? BottomSheetDialog)?.behavior?.apply {
            state = BottomSheetBehavior.STATE_EXPANDED
            isHideable = true
            skipCollapsed = true
            isFitToContents = false
            setExpandedOffset(resources.getDimensionPixelSize(R.dimen.space_96x))
        }
        viewModel.countryList.observe(viewLifecycleOwner, ::setUpCountriesAdapter)
        binding.searchCountryInput.doAfterSearchQueryChange {
            viewModel.searchCountryByName(it.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCountries()
    }

    private fun setUpCountriesAdapter(countriesList: List<Country>) {
        binding.emptyResultText.visibility = if (countriesList.isEmpty()) View.VISIBLE else View.GONE
        countriesAdapter = CountriesAdapter(imageLoader) {
            viewModel.navigateToSelectCountry(it)
            findNavController().navigateUp()
        }
        countriesAdapter?.items = countriesList
        binding.countriesList.apply {
            adapter = countriesAdapter
            setHasFixedSize(true)
        }
    }
}
package com.atef.clubhouse.ui.country

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.atef.clubhouse.base.BaseViewModel
import com.atef.clubhouse.base.Navigation
import com.atef.clubhouse.base.extension.mutable
import com.atef.clubhouse.domain.entity.country.Country
import com.atef.clubhouse.domain.feature.country.CountriesUseCase
import com.atef.clubhouse.domain.feature.country.SearchCountryByNameUseCase
import kotlinx.coroutines.launch

class CountryViewModel @ViewModelInject constructor(
    private val countriesUseCase: CountriesUseCase,
    private val searchCountryByNameUseCase: SearchCountryByNameUseCase,
) : BaseViewModel<Navigation>() {

    val countryList = mutable<List<Country>>(emptyList())
    val selectedCountry = mutable<Country>()

    init {
        getCountries()
    }

    fun getCountries() {
        viewModelScope.launch {
            countryList.postValue(countriesUseCase())
        }
    }

    fun searchCountryByName(countryName: String) {
        viewModelScope.launch {
            if (countryName.isNotEmpty())
                countryList.postValue(searchCountryByNameUseCase(SearchCountryByNameUseCase.Params(countryName)))
            else
                countryList.postValue(countriesUseCase())
        }
    }

    fun navigateToSelectCountry(country: Country) {
        selectedCountry.postValue(country)
    }

}
package com.atef.clubhouse.data.local.feature.country

import android.content.Context
import com.atef.clubhouse.R
import com.atef.clubhouse.domain.entity.country.Country
import com.atef.clubhouse.domain.repository.CountryRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson,
    private val mapper: CountriesMapper,
) : CountryRepository {

    private fun readCountriesFromAssets(): String {
        return context.resources.openRawResource(R.raw.country_codes).bufferedReader().use {
            it.readText()
        }
    }

    override suspend fun getCountries(): List<Country> {
        val countriesJson = readCountriesFromAssets()
        val countriesList =
            gson.fromJson<List<CountryEntity>>(countriesJson, object : TypeToken<List<CountryEntity>>() {}.type)
        return countriesList.map { mapper.mapFromModel(it) }
    }

    override suspend fun getCurrentCountry(): Country {
        val locale = context.resources.configuration.locale.country
        val allCountries = getCountries()
        return allCountries.firstOrNull { it.code == locale }
            ?: allCountries.first { it.code == "EG" }
    }
}
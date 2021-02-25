package com.atef.clubhouse.domain.repository

import com.atef.clubhouse.domain.entity.country.Country

interface CountryRepository {
    suspend fun getCountries(): List<Country>
    suspend fun getCurrentCountry(): Country
}
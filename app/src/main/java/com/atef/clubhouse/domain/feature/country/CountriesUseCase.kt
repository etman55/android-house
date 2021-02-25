package com.atef.clubhouse.domain.feature.country

import com.atef.clubhouse.domain.base.coroutines.CoroutineDispatcherProvider
import com.atef.clubhouse.domain.entity.country.Country
import com.atef.clubhouse.domain.base.usecase.SuspendingInteractor
import com.atef.clubhouse.domain.repository.CountryRepository
import javax.inject.Inject

class CountriesUseCase @Inject constructor(
    private val countryRepository: CountryRepository,
    dispatcher: CoroutineDispatcherProvider
) : SuspendingInteractor<Nothing?, List<Country>>(dispatcher) {
    override suspend fun execute(params: Nothing?): List<Country> {
        return countryRepository.getCountries()
    }
}
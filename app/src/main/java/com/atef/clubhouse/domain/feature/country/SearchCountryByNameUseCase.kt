package com.atef.clubhouse.domain.feature.country

import com.atef.clubhouse.domain.base.coroutines.CoroutineDispatcherProvider
import com.atef.clubhouse.domain.base.usecase.SuspendingInteractor
import com.atef.clubhouse.domain.entity.country.Country
import com.atef.clubhouse.domain.repository.CountryRepository
import javax.inject.Inject

class SearchCountryByNameUseCase @Inject constructor(
    private val countryRepository: CountryRepository,
    dispatcher: CoroutineDispatcherProvider,
) : SuspendingInteractor<SearchCountryByNameUseCase.Params, List<Country>>(dispatcher) {

    data class Params(val name: String)

    override suspend fun execute(params: Params?): List<Country> {
        requireNotNull(params)
        return countryRepository.getCountries().filter { country ->
            country.name.contains(params.name, ignoreCase = true)
        }
    }
}
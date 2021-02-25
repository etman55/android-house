package com.atef.clubhouse.domain.feature.country

import com.atef.clubhouse.domain.base.coroutines.CoroutineDispatcherProvider
import com.atef.clubhouse.domain.base.usecase.SuspendingInteractor
import com.atef.clubhouse.domain.entity.country.Country
import com.atef.clubhouse.domain.repository.CountryRepository
import javax.inject.Inject

class CurrentCountryUseCase @Inject constructor(
    private val countryRepository: CountryRepository,
    dispatcher: CoroutineDispatcherProvider,
) : SuspendingInteractor<Nothing?, Country>(dispatcher) {
    override suspend fun execute(params: Nothing?): Country {
        return countryRepository.getCurrentCountry()
    }
}
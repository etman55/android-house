package com.atef.clubhouse.data.local.feature.country

import com.atef.clubhouse.data.base.mapper.RemoteModelMapper
import com.atef.clubhouse.domain.entity.country.Country
import javax.inject.Inject

class CountriesMapper @Inject constructor() : RemoteModelMapper<CountryEntity, Country> {
    override fun mapFromModel(model: CountryEntity): Country {
        return with(model) {
            Country(name = name,
                dialCode = dial_code,
                code = code,
                emojii = getFlagEmoji(code))
        }
    }
}

fun getFlagEmoji(country: String): String {
    val firstLetter = Character.codePointAt(country, 0) - 0x41 + 0x1F1E6
    val secondLetter = Character.codePointAt(country, 1) - 0x41 + 0x1F1E6
    return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
}
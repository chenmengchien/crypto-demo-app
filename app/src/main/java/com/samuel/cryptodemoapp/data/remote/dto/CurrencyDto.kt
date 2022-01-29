package com.samuel.cryptodemoapp.data.remote.dto

import com.samuel.cryptodemoapp.data.local.entity.CurrencyEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyDto(
    val id: String,
    val name: String,
    val symbol: String
) {
    fun toCurrencyEntity(): CurrencyEntity {
        return CurrencyEntity(
            id = id,
            name = name,
            symbol = symbol
        )
    }
}

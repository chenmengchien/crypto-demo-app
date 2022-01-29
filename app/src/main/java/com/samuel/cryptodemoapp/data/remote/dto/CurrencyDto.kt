package com.samuel.cryptodemoapp.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyDto(
    val id: String,
    val name: String,
    val symbol: String
)

package com.samuel.cryptodemoapp.data.remote

import com.samuel.cryptodemoapp.data.remote.dto.CurrencyDto
import retrofit2.http.GET

interface CurrencyService {

    @GET("/mock/currencyList")
    suspend fun getCurrencyList(): List<CurrencyDto>
}

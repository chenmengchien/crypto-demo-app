package com.samuel.cryptodemoapp.data.remote

import com.samuel.cryptodemoapp.data.remote.dto.CurrencyDto
import retrofit2.http.GET
import retrofit2.http.Headers

interface CurrencyService {

    @Headers("mock:true")
    @GET("/mock/currencyList")
    suspend fun getCurrencyList(): List<CurrencyDto>
}

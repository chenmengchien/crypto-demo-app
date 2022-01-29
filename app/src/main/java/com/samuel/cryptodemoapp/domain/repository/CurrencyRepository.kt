package com.samuel.cryptodemoapp.domain.repository

import com.samuel.cryptodemoapp.domain.model.Currency
import com.samuel.cryptodemoapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    fun getCurrencyList(): Flow<Resource<List<Currency>>>
}

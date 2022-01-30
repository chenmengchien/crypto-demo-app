package com.samuel.cryptodemoapp

import com.samuel.cryptodemoapp.domain.model.Currency
import com.samuel.cryptodemoapp.domain.repository.CurrencyRepository
import com.samuel.cryptodemoapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCurrencyRepository : CurrencyRepository {

    private val currencyList = mutableListOf<Currency>()

    override fun getCurrencyList(): Flow<Resource<List<Currency>>> {
        return flow {
            emit(Resource.Loading(currencyList.toList()))
            emit(Resource.Success(currencyList.toList()))
        }
    }

    override fun getCurrencyDetail(currencyId: String): Flow<Currency?> {
        return flow { emit(currencyList.find { it.id == currencyId }) }
    }

    fun setData (data: List<Currency>) {
        currencyList.addAll(data)
    }

}

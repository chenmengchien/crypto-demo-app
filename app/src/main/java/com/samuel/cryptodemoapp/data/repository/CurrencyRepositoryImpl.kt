package com.samuel.cryptodemoapp.data.repository

import android.util.Log
import com.samuel.cryptodemoapp.data.local.CurrencyDao
import com.samuel.cryptodemoapp.data.local.entity.CurrencyEntity
import com.samuel.cryptodemoapp.data.remote.CurrencyService
import com.samuel.cryptodemoapp.domain.model.Currency
import com.samuel.cryptodemoapp.domain.repository.CurrencyRepository
import com.samuel.cryptodemoapp.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CurrencyRepositoryImpl(
    private val service: CurrencyService,
    private val dao: CurrencyDao
) : CurrencyRepository {

    override fun getCurrencyList(): Flow<Resource<List<Currency>>> = flow {
        emit(Resource.Loading())
        delay(3000L)
        val currencyList = dao.getAll().map { it.toCurrency() }
        emit(Resource.Loading(currencyList))

        runCatching {
            service.getCurrencyList()
        }.onSuccess { result ->
            dao.insertAll(result.filterNotNull().map { it.toCurrencyEntity() })
            val newData = dao.getAll().map { it.toCurrency() }
            emit(
                Resource.Success(
                    data = newData
                )
            )
        }.onFailure {
            Log.e(CurrencyRepositoryImpl::class.simpleName, "getCurrencyList: $it")
            emit(
                Resource.Error(
                    message = "something went wrong!",
                    data = currencyList
                )
            )
        }
    }

    override fun getCurrencyDetail(currencyId: String): Flow<Currency> = flow {
        val result = dao.getCurrency(currencyId)
        emit(result.toCurrency())
    }
}

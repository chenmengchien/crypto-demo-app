package com.samuel.cryptodemoapp.domain.use_case

import com.samuel.cryptodemoapp.domain.OrderType
import com.samuel.cryptodemoapp.util.Resource
import com.samuel.cryptodemoapp.domain.model.Currency
import com.samuel.cryptodemoapp.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCurrencyListUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    operator fun invoke(
        orderType: OrderType
    ): Flow<Resource<List<Currency>>> = flow {
        repository.getCurrencyList().collect { result ->
            val sortingData = result.data?.run {
                when (orderType) {
                    OrderType.Ascending -> sortedBy { it.name.lowercase() }
                    OrderType.Descending -> sortedByDescending { it.name.lowercase() }
                }
            } ?: emptyList()

            emit(
                when (result) {
                    is Resource.Error -> Resource.Error(
                        data = sortingData,
                        message = result.message
                    )
                    is Resource.Loading -> Resource.Loading(data = sortingData)
                    is Resource.Success -> Resource.Success(data = sortingData)
                }
            )
        }
    }
}

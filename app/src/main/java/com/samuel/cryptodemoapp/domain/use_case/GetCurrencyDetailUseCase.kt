package com.samuel.cryptodemoapp.domain.use_case

import com.samuel.cryptodemoapp.domain.model.Currency
import com.samuel.cryptodemoapp.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrencyDetailUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    operator fun invoke(currencyId: String): Flow<Currency?> {
        return repository.getCurrencyDetail(currencyId)
    }
}

package com.samuel.cryptodemoapp.presentation.currency_list

import com.samuel.cryptocurrency.domain.OrderType
import com.samuel.cryptodemoapp.domain.model.Currency

data class CurrencyListState(
    val currencyList: List<Currency> = emptyList(),
    val isLoading: Boolean = false,
    val orderType: OrderType = OrderType.Descending
)

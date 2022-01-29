package com.samuel.cryptodemoapp.presentation.currency_list

import com.samuel.cryptodemoapp.domain.OrderType

sealed class CurrencyListEvent {
    class Order(val orderType: OrderType) : CurrencyListEvent()
    object PullCurrencyList: CurrencyListEvent()
}

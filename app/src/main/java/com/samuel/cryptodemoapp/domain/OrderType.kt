package com.samuel.cryptodemoapp.domain

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}

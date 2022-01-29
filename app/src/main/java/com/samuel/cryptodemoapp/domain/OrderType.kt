package com.samuel.cryptocurrency.domain

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}

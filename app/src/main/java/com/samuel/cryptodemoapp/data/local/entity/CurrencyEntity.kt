package com.samuel.cryptodemoapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.samuel.cryptodemoapp.domain.model.Currency

@Entity(tableName = "currency")
data class CurrencyEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val symbol: String
) {
    fun toCurrency(): Currency {
        return Currency(
            id = id,
            name = name,
            symbol = symbol
        )
    }
}

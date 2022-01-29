package com.samuel.cryptodemoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.samuel.cryptodemoapp.data.local.entity.CurrencyEntity

@Database(
    entities = [CurrencyEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}

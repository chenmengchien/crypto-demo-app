package com.samuel.cryptodemoapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.samuel.cryptodemoapp.data.local.entity.CurrencyEntity
import com.samuel.cryptodemoapp.domain.model.Currency
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(currencyList: List<CurrencyEntity>)

    @Query("SELECT * FROM currency WHERE currency.id = :currencyId")
    suspend fun getCurrency(currencyId: String): CurrencyEntity

    @Query("SELECT * FROM currency")
    suspend fun getAll(): List<CurrencyEntity>
}

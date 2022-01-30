package com.samuel.cryptodemoapp

import com.samuel.cryptodemoapp.domain.OrderType
import com.samuel.cryptodemoapp.domain.model.Currency
import com.samuel.cryptodemoapp.domain.use_case.GetCurrencyListUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetCurrencyListTest {
    private lateinit var getCurrencyListUseCase: GetCurrencyListUseCase
    private lateinit var fakeCurrencyRepository: FakeCurrencyRepository

    @Before
    fun setUp() {
        fakeCurrencyRepository = FakeCurrencyRepository()
        getCurrencyListUseCase = GetCurrencyListUseCase(fakeCurrencyRepository)

        val currencyList = mutableListOf<Currency>()
        ('a'..'z').forEachIndexed { index, c ->
            currencyList.add(
                Currency(
                    id = c.toString(),
                    name = c.toString() + index,
                    symbol = index.toString()
                )
            )
        }

        currencyList.shuffle()

        fakeCurrencyRepository.setData(currencyList)
    }

    @Test
    fun `Order currency by id ascending`() = runBlocking {
        val currencyList = getCurrencyListUseCase(OrderType.Ascending).first().data!!

        for (i in 0..currencyList.size - 2) {
            assertTrue(currencyList[i].id < currencyList[i + 1].id)
        }
    }

    @Test
    fun `Order currency by id descending`() = runBlocking {
        val currencyList = getCurrencyListUseCase(OrderType.Descending).first().data!!

        for (i in 0..currencyList.size - 2) {
            assertTrue(currencyList[i].id > currencyList[i + 1].id)
        }
    }
}

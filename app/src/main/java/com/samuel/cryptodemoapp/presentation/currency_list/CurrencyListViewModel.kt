package com.samuel.cryptodemoapp.presentation.currency_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samuel.cryptodemoapp.domain.OrderType
import com.samuel.cryptodemoapp.util.Resource
import com.samuel.cryptodemoapp.domain.use_case.GetCurrencyListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CurrencyListViewModel @Inject constructor(
    private val getCurrencyListUseCase: GetCurrencyListUseCase
) : ViewModel() {

    private val _state = mutableStateOf(value = CurrencyListState())
    val currencyListState: State<CurrencyListState> = _state

    private var getCurrencyListJob: Job? = null

    fun onEvent(event: CurrencyListEvent) {
        when (event) {
            is CurrencyListEvent.Order -> {
                if (_state.value.orderType == event.orderType) {
                    return
                }
                getCurrencyList(orderType = event.orderType)
            }
            is CurrencyListEvent.PullCurrencyList -> {
                getCurrencyList(orderType = OrderType.Descending)
            }
        }
    }

    private fun getCurrencyList(orderType: OrderType) {
        getCurrencyListJob?.cancel()
        getCurrencyListJob = getCurrencyListUseCase(orderType).onEach {
            when (it) {
                is Resource.Error -> {
                    _state.value = CurrencyListState(
                        currencyList = it.data ?: emptyList(),
                        isLoading = false,
                        orderType = orderType
                    )
                }
                is Resource.Loading -> {
                    _state.value = CurrencyListState(
                        currencyList = it.data ?: emptyList(),
                        isLoading = true,
                        orderType = orderType
                    )
                }
                is Resource.Success -> {
                    _state.value = CurrencyListState(
                        currencyList = it.data ?: emptyList(),
                        isLoading = false,
                        orderType = orderType
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}

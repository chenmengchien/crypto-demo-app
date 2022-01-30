package com.samuel.cryptodemoapp.presentation.currency_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samuel.cryptodemoapp.common.Constants
import com.samuel.cryptodemoapp.domain.use_case.GetCurrencyDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CurrencyDetailViewModel @Inject constructor(
    val getCurrencyDetailUseCase: GetCurrencyDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(CurrencyDetailState())
    val state: State<CurrencyDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_CURRENCY_ID)?.let {
            getCurrency(it)
        }
    }

    private fun getCurrency(currencyId: String) {
        getCurrencyDetailUseCase(currencyId).onEach {
            _state.value = CurrencyDetailState(it)
        }.launchIn(viewModelScope)
    }
}

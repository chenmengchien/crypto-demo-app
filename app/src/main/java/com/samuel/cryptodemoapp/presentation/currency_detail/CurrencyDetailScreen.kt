package com.samuel.cryptodemoapp.presentation.currency_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CurrencyDetailScreen(
    viewModel: CurrencyDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        state.currency?.let {
            Column {
                Text(text = it.id)
                Text(text = it.name)
                Text(text = it.symbol)
            }
        }
    }
}

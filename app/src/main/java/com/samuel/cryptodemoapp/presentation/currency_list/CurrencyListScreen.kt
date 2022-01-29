package com.samuel.cryptodemoapp.presentation.currency_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.samuel.cryptocurrency.domain.OrderType
import com.samuel.cryptodemoapp.R
import com.samuel.cryptodemoapp.presentation.currency_list.component.CurrencyItem

@Composable
fun CurrencyListScreen(
    navController: NavController,
    viewModel: CurrencyListViewModel = hiltViewModel()
) {

    val state = viewModel.currencyListState.value

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(state.currencyList) { index, currency ->
                    CurrencyItem(
                        currency = currency,
                        onItemClick = {

                        }
                    )
                    if (index < state.currencyList.size) {
                        Divider()
                    }
                }
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { viewModel.onEvent(CurrencyListEvent.PullCurrencyList) },
                modifier = Modifier
                    .weight(1f)
                    .padding(2.dp)
            ) {
                Text(text = stringResource(id = R.string.button_get_data))
            }
            Button(
                onClick = {
                    viewModel.onEvent(
                        CurrencyListEvent.Order(
                            if (state.orderType == OrderType.Ascending) {
                                OrderType.Descending
                            } else {
                                OrderType.Ascending
                            }
                        )
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(2.dp)
            ) {
                Text(text = stringResource(id = R.string.button_change_order))
            }
        }
    }
}

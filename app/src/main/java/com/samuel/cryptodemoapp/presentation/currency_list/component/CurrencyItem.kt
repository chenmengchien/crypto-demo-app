package com.samuel.cryptodemoapp.presentation.currency_list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samuel.cryptodemoapp.domain.model.Currency

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CurrencyItem(
    currency: Currency,
    modifier: Modifier = Modifier,
    onItemClick: (id: String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable { onItemClick.invoke(currency.id) }
            .padding(14.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        ) {
            Text(
                text = currency.name.first().toString().uppercase(),
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            text = currency.name,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = currency.symbol,
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            text = ">",
            style = MaterialTheme.typography.h6,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyItemPreview() {
    CurrencyItem(
        currency = Currency(id = "BTC", name = "BTC COIN", symbol = "BTC"),
        onItemClick = {}
    )
}

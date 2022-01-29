package com.samuel.cryptodemoapp

sealed class Screen(val route: String) {
    object CurrencyListScreen: Screen("currency_list_screen")
    object CurrencyDetailScreen: Screen("currency_detail_screen")
}


package com.samuel.cryptodemoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samuel.cryptodemoapp.common.Constants
import com.samuel.cryptodemoapp.presentation.currency_detail.CurrencyDetailScreen
import com.samuel.cryptodemoapp.presentation.currency_list.CurrencyListScreen
import com.samuel.cryptodemoapp.ui.theme.CryptoDemoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoDemoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CurrencyListScreen.route,
                    ) {
                        composable(
                            route = Screen.CurrencyListScreen.route
                        ) {
                            CurrencyListScreen(navController = navController)
                        }
                        composable(
                            route = "${Screen.CurrencyDetailScreen.route}/{${
                                Constants.PARAM_CURRENCY_ID
                            }}"
                        ) {
                            CurrencyDetailScreen()
                        }
                    }
                }
            }
        }
    }
}

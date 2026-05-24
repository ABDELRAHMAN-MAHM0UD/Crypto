package com.example.crypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.crypto.presentation.Screen
import com.example.crypto.presentation.coin_detail.CoinDetailScreen
import com.example.crypto.presentation.coin_list.CoinListScreen
import com.example.crypto.presentation.coin_list.CoinListViewModel
import com.example.crypto.ui.theme.CryptoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTheme {

                Surface(color = MaterialTheme.colorScheme.background) {
                 val navController = rememberNavController()
                    NavHost(navController = navController,
                        startDestination = Screen.CoinListScreen.route){
                            composable(route = Screen.CoinListScreen.route){
                                val viewModel: CoinListViewModel = hiltViewModel()
                                CoinListScreen(navController = navController, viewModel = viewModel)                            }
                            composable(route = Screen.CoinDetailScreen.route + "/{coinId}"){
                                CoinDetailScreen()
                            }
                    }
                }
            }
        }
    }
}

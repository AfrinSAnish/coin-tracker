package com.example.coin_track.di.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coin_track.presentation.composable.CoinItem

@Composable
fun CoinListScreen(
    viewModel: CoinListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    // ✅ automatically switches between light and dark
    val colors = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(colors.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.surface)
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Coin Tracker",
                color = colors.onSurface,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            TextButton(onClick = { viewModel.loadCoins() }) {
                Text("Refresh", color = colors.primary)
            }
        }

        when (val s = state) {
            is CoinListState.Loading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = colors.primary)
                }
            }
            is CoinListState.Success -> {
                LazyColumn(Modifier.fillMaxSize()) {
                    items(s.coins) { coin ->
                        CoinItem(coin = coin)
                        HorizontalDivider(
                            color = colors.outlineVariant,
                            thickness = 0.5.dp
                        )
                    }
                }
            }
            is CoinListState.Error -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Something went wrong", color = colors.error, fontSize = 16.sp)
                        Spacer(Modifier.height(8.dp))
                        Text(s.message, color = colors.onSurfaceVariant, fontSize = 12.sp)
                        Spacer(Modifier.height(16.dp))
                        Button(
                            onClick = { viewModel.loadCoins() },
                            colors = ButtonDefaults.buttonColors(containerColor = colors.primary)
                        ) { Text("Retry") }
                    }
                }
            }
        }
    }
}
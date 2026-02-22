package com.example.coin_track.presentation.composable

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coin_track.di.domain.model.Coin
import com.example.coin_track.di.presentation.composable.PriceChange
import com.example.coin_track.di.presentation.utils.composable.model.CoinUI
import com.example.coin_track.di.presentation.utils.composable.model.toCoinUI

@Composable
fun CoinItem(modifier: Modifier = Modifier, coin: CoinUI) {
    val contentTextColor = if(isSystemInDarkTheme()) Color.White else Color.Black
    Row(modifier = modifier.padding(15.dp),verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)){
        Icon(
            imageVector = ImageVector.vectorResource(id = coin.icon),
                    contentDescription = coin.name,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(80.dp)
        )
        Column(modifier=Modifier.weight(1f), verticalArrangement = Arrangement.Center){
            Text(text = coin.name, color = contentTextColor, fontSize = 20.sp,fontWeight= FontWeight.Bold)
            Text(text=coin.symbol, color = contentTextColor, fontSize = 16.sp, fontWeight = FontWeight.Thin)
        }
        Column(modifier=Modifier.weight(1f), verticalArrangement = Arrangement.Center){
            Text(text=coin.priceUsd.formattedValue,color = contentTextColor,fontSize=20.sp,fontWeight = FontWeight.Bold)
            PriceChange(changePrice = coin.changePercent24Hr,)
        }
    }
}

@PreviewLightDark
@Composable
fun CoinItemScreenPreview() {
    CoinItem(coin = previewCoin)
}

private val previewCoin = Coin(
    id = "1",
    rank = 1,
    name = "BitCoin",
    symbol = "BTC",
    marketCapUsd = 123.45,
    priceUsd = 345.67,
    changePercent24Hr = 12.34
).toCoinUI()
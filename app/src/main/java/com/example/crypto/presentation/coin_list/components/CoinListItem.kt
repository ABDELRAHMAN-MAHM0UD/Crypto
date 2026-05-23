package com.example.crypto.presentation.coin_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.crypto.domain.model.Coin

@Composable
    fun ListItem(
        coin: Coin,
        onItemClick:(Coin)-> Unit
    ){
        Row(modifier = Modifier.fillMaxWidth().clickable{
            onItemClick(coin)
        },
            horizontalArrangement = Arrangement.SpaceBetween
            ){
            Text(text = "${coin.rank}. ${coin.name} (${coin.symbol})")
            Text(text = if(coin.is_active) "active" else "inactive", textAlign = TextAlign.End,
                modifier = Modifier.align(CenterVertically)
                )
        }
    }
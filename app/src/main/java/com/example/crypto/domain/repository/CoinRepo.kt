package com.example.crypto.domain.repository

import com.example.crypto.data.remote.dto.CoinDetailsDto
import com.example.crypto.data.remote.dto.CoinDto

interface  CoinRepo {
    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinById(coinId: String):CoinDetailsDto
}
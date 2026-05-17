package com.example.crypto.presentation.coin_list

import androidx.lifecycle.ViewModel
import com.example.crypto.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Coin_list_view_model @Inject constructor(private val getCoinsUseCase: GetCoinUseCase):
    ViewModel() {

}
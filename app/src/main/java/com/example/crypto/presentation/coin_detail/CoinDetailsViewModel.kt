package com.example.crypto.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope // ضروري عشان الـ launchIn
import com.example.crypto.common.Constants
import com.example.crypto.common.Resources
import com.example.crypto.domain.model.CoinDetails
import com.example.crypto.domain.use_case.get_coin.GetCoinUseCase
// 1. صلحنا الـ Import ليكون للقائمة كاملة
import com.example.crypto.domain.use_case.get_coins.GetCoinsUseCase
import com.example.crypto.presentation.coin_list.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailsState())
    val state: State<CoinDetailsState> = _state

    // 2. عملنا init عشان الدالة تشتتغل أوتوماتيك أول ما الـ ViewModel يتولد
    init {
        savedStateHandle.get<String>(Constants.PARM_ID)?.let{coinId ->
            getCoin(coinId) //
        }
    }

    private fun getCoin(coinId: String) {
        getCoinUseCase(coinId).onEach { result ->
            when (result) {
                is Resources.Success -> {
                    // 3. شيلنا القوس الزيادة
                    _state.value = CoinDetailsState(coin = result.data)
                }
                is Resources.Failed -> { // 4. عدلناها لـ Error بناءً على الـ Standard
                    _state.value = CoinDetailsState(error = result.message?: "an error ocurred")
                }
                is Resources.Loading -> {
                    _state.value = CoinDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope) // 5. ضفنا الـ launchIn عشان الماسورة تشتغل
    }
}
package com.example.crypto.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope // ضروري عشان الـ launchIn
import com.example.crypto.common.Resources
// 1. صلحنا الـ Import ليكون للقائمة كاملة
import com.example.crypto.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase // صلحنا الاسم هنا كمان
) : ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    // 2. عملنا init عشان الدالة تشتتغل أوتوماتيك أول ما الـ ViewModel يتولد
    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resources.Success -> {
                    // 3. شيلنا القوس الزيادة
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resources.Failed -> { // 4. عدلناها لـ Error بناءً على الـ Standard
                    _state.value = CoinListState(error = result.message ?: "An unexpected error occurred")
                }
                is Resources.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope) // 5. ضفنا الـ launchIn عشان الماسورة تشتغل
    }
}
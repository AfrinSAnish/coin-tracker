package com.example.coin_track.di.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coin_track.di.domain.model.repository.CoinRepository
import com.example.coin_track.di.presentation.utils.composable.model.CoinUI
import com.example.coin_track.di.presentation.utils.composable.model.toCoinUI
import com.example.coin_track.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val repository: CoinRepository
) : ViewModel() {

    private val _state = MutableStateFlow<CoinListState>(CoinListState.Loading)
    val state: StateFlow<CoinListState> = _state

    init { loadCoins() }

    fun loadCoins() {
        viewModelScope.launch {
            _state.value = CoinListState.Loading
            when (val result = repository.getCoinList()) {
                is NetworkResult.Success -> {
                    result.data?.collect { listOfCoin ->
                        _state.value = CoinListState.Success(listOfCoin.data.map { it.toCoinUI() })
                    }
                }
                is NetworkResult.Error -> _state.value = CoinListState.Error(result.message ?: "Unknown error")
                is NetworkResult.Loading -> _state.value = CoinListState.Loading
            }
        }
    }
}

sealed class CoinListState {
    object Loading : CoinListState()
    data class Success(val coins: List<CoinUI>) : CoinListState()
    data class Error(val message: String) : CoinListState()
}
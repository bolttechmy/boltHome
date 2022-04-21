package com.bolttech.bolthome.data

sealed class UiState {
    data class Content(val data: Any) : UiState()
    data class Error(val message: String) : UiState()
    object Loading : UiState()
    object Empty : UiState()
}
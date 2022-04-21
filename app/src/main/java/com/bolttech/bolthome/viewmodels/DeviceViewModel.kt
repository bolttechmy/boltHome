package com.bolttech.bolthome.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bolttech.bolthome.BoltHomeApplication.Companion.appComponent
import com.bolttech.bolthome.data.Device
import com.bolttech.bolthome.data.UiState
import com.bolttech.bolthome.repositories.DashBoardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


class DashBoardViewModel : ViewModel() {

    init {
        appComponent.inject(this)
        getData()
    }

    @Inject
    lateinit var dashBoardRepository: DashBoardRepository
    val uiState = MutableStateFlow<UiState>(UiState.Loading)
    lateinit var mResponse: Device
    private fun getData() = viewModelScope.launch {

        flow {
            emit(UiState.Loading)
            try {
                mResponse = dashBoardRepository.getDevice()
                emit(UiState.Content(mResponse))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(UiState.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO).collect {
            uiState.value = it
        }
    }
}
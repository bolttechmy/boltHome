package com.bolttech.bolthome.viewmodels

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bolttech.bolthome.data.Node
import com.bolttech.bolthome.data.UiState
import com.bolttech.bolthome.repositories.DashBoardRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Type
import javax.inject.Inject


class ScanResultViewModel : ViewModel() {


    @Inject
    lateinit var dashBoardRepository: DashBoardRepository
    val uiState = MutableStateFlow<UiState>(UiState.Empty)

    fun doScan(activity: ComponentActivity) =
        viewModelScope.launch {
//            callbackFlow {
//                awaitClose { channel.close() }
//            }.flowOn(Dispatchers.IO).collect {
//                uiState.value = it
//            }
        }
}
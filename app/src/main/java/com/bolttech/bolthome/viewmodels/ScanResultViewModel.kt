package com.bolttech.bolthome.viewmodels

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bolttech.bolthome.data.Node
import com.bolttech.bolthome.data.UiState
import com.bolttech.bolthome.repositories.DashBoardRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
//import com.overlook.android.fingkit.FingScanOptions
//import com.overlook.android.fingkit.FingScanResultLevel
//import com.overlook.android.fingkit.FingScanner
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
//    private val scanner: FingScanner = FingScanner.getInstance()

    fun doScan(activity: ComponentActivity) =
        viewModelScope.launch {
//            callbackFlow {
//                val license = "CGJvbHR0ZWNoCUJvbHRUZWNoIAxtb2JpbGUtdHJpYWwAAAFyVV2pPH68iLY="
//                trySend(UiState.Loading)
//                val scanCallback: FingScanner.FingResultCallback =
//                    FingScanner.FingResultCallback { data: String?, e: Exception? ->
//                        data?.let { s ->
//                            val obj = JSONObject(s)
//                            if (obj.getBoolean("completed") && obj.getInt("progress") > 0) {
//                                print(obj)
//                                val nodeString = obj["nodes"] as JSONArray
//                                val nodeListType: Type =
//                                    object : TypeToken<ArrayList<Node>?>() {}.type
//
//                                val devices: ArrayList<Node> =
//                                    Gson().fromJson(nodeString.toString(), nodeListType)
//                                trySendBlocking(UiState.Content(devices))
//                            }
//                        } ?: trySendBlocking(UiState.Error(e?.message ?: "Error    ...."))
//
//                    }
//                val validateCallback: FingScanner.FingResultCallback =
//                    FingScanner.FingResultCallback { _: String?, e: Exception? ->
//                        if (e == null)
//                        //validated succesfully
//                        {
//                            val options: FingScanOptions = FingScanOptions().apply {
//                                resultLevelScanInProgress = FingScanResultLevel.NONE
//                                resultLevelScanCompleted = FingScanResultLevel.FULL
//                            }
//                            scanner.networkScan(options, scanCallback)
//                        } else e.printStackTrace()
//
//                    }
//
//                if (scanner.isConnected) scanner.validateLicenseKey(
//                    license,
//                    null,
//                    validateCallback
//                ) else scanner.connect(
//                    activity
//                ) { _, _: Exception? ->
//                    scanner.validateLicenseKey(
//                        license,
//                        null,
//                        validateCallback
//                    )
//                }
//                awaitClose { channel.close() }
//            }.flowOn(Dispatchers.IO).collect {
//                uiState.value = it
//            }
        }
}
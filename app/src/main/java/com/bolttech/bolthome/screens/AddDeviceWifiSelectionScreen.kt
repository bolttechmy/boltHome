package com.bolttech.bolthome.screens

import android.content.Context
import android.net.wifi.SupplicantState
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bolttech.bolthome.R
import com.bolttech.bolthome.component.Page
import com.bolttech.bolthome.data.Node
import com.bolttech.bolthome.data.UiState
import com.bolttech.bolthome.ui.theme.Typography
import com.bolttech.bolthome.viewmodels.ScanResultViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import java.util.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AddDeviceWifiSelectionScreen(navController: NavController) {

    val activity = LocalContext.current as ComponentActivity
    val scanResultViewModel by remember { (activity).viewModels<ScanResultViewModel>() }
    // Wifi permission state
    val wifiPermissionStat = rememberPermissionState(
        android.Manifest.permission.ACCESS_WIFI_STATE
    )

    when (wifiPermissionStat.status) {
        // If the Wifi permission is granted, then show screen with the feature enabled
        PermissionStatus.Granted -> {
            val ssid = remember { mutableStateOf("") }
            val wifiManager =
                LocalContext.current.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager?
            try {
                val wifiInfo: WifiInfo = wifiManager!!.connectionInfo
                if (wifiInfo.supplicantState == SupplicantState.COMPLETED) {
                    ssid.value = wifiInfo.ssid
                }
            } catch (e: Exception) {
                e.printStackTrace()

            }
            Page {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {

                        when (val result =
                            scanResultViewModel.uiState.collectAsState().value) {
                            is UiState.Content ->
                                DeviceList(
                                    nodes = (result.data as? List<*>)?.filterIsInstance<Node>()
                                        ?: emptyList()
                                )
                            is UiState.Error -> Text(text = result.message)
                            UiState.Loading -> CircularProgressIndicator()
                            UiState.Empty -> AddDeviceWifi(
                                activity = activity,
                                ssid = ssid,
                                scanResultViewModel = scanResultViewModel
                            )
                        }

                    }
                }
            }


        }
        is PermissionStatus.Denied -> {
            run { wifiPermissionStat.launchPermissionRequest() }

        }
    }

}

@Composable
private fun DeviceList(nodes: List<Node>) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier.fillMaxSize()
        ) {

            Column(
                modifier = Modifier.matchParentSize(), horizontalAlignment = Alignment.Start

            ) {

                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row {
                        Text(
                            text = "${nodes.size} devices found",
                            style = MaterialTheme.typography.h6
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Image(painterResource(R.drawable.ic_network), "")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "Can't find your device? add it manually",
                        style = MaterialTheme.typography.caption
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = Modifier.background(color = Color(0xFFE8E7EE))) {
                    Row(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
                        Image(painterResource(R.drawable.ic_circle_alert), "")
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "We found 2 devices that we could not identify.\nYou'll need to add this manually.",
                            style = MaterialTheme.typography.caption
                        )
                    }
                }
                Column {

                    NodeCard(name = "Select all devices")
                    Divider()
                    LazyColumn(modifier = Modifier.weight(weight = 1f, fill = false)) {
                        items(nodes.size) {
                            val node = nodes[it]
                            NodeCard(
                                type = node.best_type,
                                name = node.best_name,
                                make = node.best_make
                            )
                            Divider()
                        }

                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                    ) {
                        OutlinedButton(modifier = Modifier.fillMaxHeight().weight(1f),
                            shape = RectangleShape,
                            onClick = {}) {
                            Text(text = "0 devices selected")
                        }
                        Button(modifier = Modifier.fillMaxHeight().weight(1f),
                            shape = RectangleShape,
                            onClick = {})
                        {
                            Row {
                                Text(text = "Add")
                                Spacer(modifier = Modifier.width(16.dp))
                                Image(
                                    painter = painterResource(R.drawable.ic_chevron_right),
                                    contentDescription = "",
                                    colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary)
                                )

                            }

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Chip(
    name: String = "Chip",
    isSelected: Boolean = false,
    onSelectionChanged: (String) -> Unit = {},
) {
    Surface(
        modifier = Modifier.padding(4.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) Color.LightGray else MaterialTheme.colors.secondary
    ) {
        Row(modifier = Modifier
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectionChanged(name)
                }
            )
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
private fun NodeCard(type: String? = "", name: String?, make: String? = null) {

    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        val res: Int = when {
            type?.lowercase(Locale.ROOT) == "mobile" || type?.lowercase(Locale.ROOT) == "smartphone" -> R.drawable.ic_mobile
            type?.lowercase(Locale.ROOT) == "television" -> R.drawable.ic_television
            type?.lowercase(Locale.ROOT) == "dvd player" -> R.drawable.ic_television
            else -> R.drawable.ic_resource_default
        }
        if (type != "") {
            Image(
                painter = painterResource(id = res),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit,
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.fillMaxWidth(.8f),
            horizontalAlignment = Alignment.Start,
        ) {

            if (type != null && type != "") Chip(name = type)

            Text(
                text = name ?: "",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
            )
            make?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body2,
                )
            }
        }
        Checkbox(
            onCheckedChange = {},
            checked = false,
        )
    }

}

@Composable
private fun AddDeviceWifi(
    activity: ComponentActivity,
    ssid: MutableState<String>,
    scanResultViewModel: ScanResultViewModel
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {

                Text(
                    "Confirm your WiFi network",
                    fontWeight = FontWeight.Bold,
                    style = Typography.h6.copy(color = MaterialTheme.colors.primary)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier.border(
                        BorderStroke(1.dp, Color.Gray)
                    )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp).wrapContentWidth()
                    ) {
                        Image(
                            painterResource(R.drawable.ic_baseline_wifi_24),
                            colorFilter = ColorFilter.tint(Color.Gray),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            ssid.value,
                            softWrap = true,
                            textAlign = TextAlign.Center,
                            style = Typography.caption
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    "We will scan this WiFi network to find your devices",
                    style = Typography.caption
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        scanResultViewModel.doScan(activity)
                        // navController.navigate(AppScreens.ScanResultScreen.route)
                    },
                    shape = RectangleShape
                ) {
                    Text("Add Devices Through WiFi", style = Typography.button)
                    Image(
                        painterResource(R.drawable.ic_chevron_right),
                        contentDescription = "Localized description",
                        Modifier.padding(start = 8.dp),
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary),
                    )
                }
            }
        }
    }
}
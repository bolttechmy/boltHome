package com.bolttech.bolthome.screens

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bolttech.bolthome.OkHttpApiResponseProvider
import com.bolttech.bolthome.R
import com.bolttech.bolthome.component.Page
import com.bolttech.bolthome.data.Device
import com.bolttech.bolthome.data.Devices
import com.bolttech.bolthome.data.UiState
import com.bolttech.bolthome.navigation.AppScreens
import com.bolttech.bolthome.ui.theme.Typography
import com.bolttech.bolthome.viewmodels.DashBoardViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


@Composable
fun DashBoardScreen(navController: NavController) {
    Page(
        floatingActionButton = {
            FloatingActionButton(modifier = Modifier.padding(24.dp), onClick = {
                navController.navigate(AppScreens.AddDevicePreWifiScreen.route)
            }) {
                Icon(imageVector = Icons.Default.Add, "")
            }
        }
    ) {
        val ac: ComponentActivity = LocalContext.current as ComponentActivity
        Surface(modifier = Modifier.fillMaxSize()) {
            val mainViewModel by remember { ac.viewModels<DashBoardViewModel>() }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                when (val result =
                    mainViewModel.uiState.collectAsState().value) {
                    is UiState.Content -> DashBoard(result.data as Device, navController = navController)
                    is UiState.Error -> Text(text = result.message)
                    UiState.Loading -> CircularProgressIndicator()
                    UiState.Empty -> {}
                }
            }
        }
    }

}


@Composable
fun DashBoard(device: Device, navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "My devices", textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            style = Typography.h5,
            modifier = Modifier.padding(16.dp),
        )
        Spacer(modifier = Modifier.width(16.dp))

        Button(onClick = {
            coroutineScope.launch {
                withContext(Dispatchers.IO){
                    Log.e("ResponseFromOkHttpLib", OkHttpApiResponseProvider().getResponseFromApiThroughOkHttpLib())
                }
            }
        }) {
            Text("Get response from OkHttp Library")
        }
//        Grid(device, navController)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Grid(device: Device, navController: NavController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
    ) {
        val deviceList = device.items.first().devices
        var pos = 0
        items(deviceList.size) {
            EachItem(deviceList[it], navController = navController, pos++)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EachItem(i: Devices, navController: NavController, pos:Int) {
    val ac: ComponentActivity = LocalContext.current as ComponentActivity
    val mainViewModel by remember { ac.viewModels<DashBoardViewModel>() }

    Card(
        shape = RectangleShape,
        elevation = 2.dp,
        onClick = {
            navController.navigate(AppScreens.DeviceDetailScreen.route+"/$pos")
        }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            val res: Int = when {
                i.deviceType?.lowercase(Locale.ROOT) == "mobile" || i.deviceType?.lowercase(Locale.ROOT) == "smartphone" -> R.drawable.ic_mobile
                i.deviceType?.lowercase(Locale.ROOT) == "television" -> R.drawable.ic_television
                i.deviceType?.lowercase(Locale.ROOT) == "dvd player" -> R.drawable.ic_television
                else -> R.drawable.ic_resource_default
            }
            Image(
                contentDescription = "",
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .height(56.dp)
                    .width(56.dp),
                painter = painterResource(res),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                style = Typography.caption,
                text = i.name?: ""
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Image(painterResource(R.drawable.ic_circle_check), "")
                Text(
                    maxLines = 2,
                    style = Typography.caption,
                    text = "${i.deviceStatus}",
                    textAlign = TextAlign.Left,
                )
            }
        }
    }
}

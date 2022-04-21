package com.bolttech.bolthome.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bolttech.bolthome.R
import com.bolttech.bolthome.component.Page
import com.bolttech.bolthome.navigation.AppScreens
import com.bolttech.bolthome.ui.theme.Typography


@Composable

fun AddDevicePreWifiScreen(navController: NavController) {
    Page {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Image(
                    contentDescription = "",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier.height(140.dp).width(140.dp),
                    painter = painterResource(R.drawable.ic_add_device),
                )
                Text(
                    "Welcome to boltHome",
                    fontWeight = FontWeight.Bold,
                    style = Typography.h6.copy(color = MaterialTheme.colors.primary)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Lets Kick start your boltHome experience \nby adding devices into the app",
                    softWrap = true,
                    textAlign = TextAlign.Center,
                    style = Typography.caption.copy(color = MaterialTheme.colors.primary)
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    "Pick a method to add devices:",
                    style = Typography.caption
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { navController.navigate(AppScreens.AddDeviceWifiSelectionScreen.route) },
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
                OutlinedButton(
                    onClick = {},
                    border = BorderStroke(1.dp, MaterialTheme.colors.primary),
                    shape = RectangleShape
                ) {
                    Text("Add A Device Manually", style = Typography.caption)
                    Image(
                        painterResource(R.drawable.ic_chevron_right),
                        contentDescription = "Localized description",
                        Modifier.padding(start = 8.dp),
                    )
                }
            }
        }
    }
}
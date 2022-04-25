package com.bolttech.bolthome.screens

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bolttech.bolthome.R
import com.bolttech.bolthome.component.Page
import com.bolttech.bolthome.data.Devices
import com.bolttech.bolthome.navigation.AppScreens
import com.bolttech.bolthome.viewmodels.DashBoardViewModel


@Composable
fun DeviceDetailScreen(navController: NavController, pos: String?) {
    val color = Color(0Xff170F4F)
    val marginHorizontal = 16.dp
    val ac: ComponentActivity = LocalContext.current as ComponentActivity
    val mainViewModel by remember { ac.viewModels<DashBoardViewModel>() }
    val device: Devices = mainViewModel.mResponse.items[0].devices[pos?.toInt()!!]

    Page {
        Column() {
            Column(modifier = Modifier.padding(horizontal = marginHorizontal)) {
                Text(
                    "${device.name}",
                    color = color,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text("${device.make} - ${device.model}")
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Column(
                modifier = Modifier
                    .background(color = Color(0XffE6F9FA))
                    .padding(horizontal = marginHorizontal, vertical = 16.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Row(horizontalArrangement = Arrangement.Start) {
                        Text("Policy", color = color, fontSize = 14.sp)
                        Image(
                            modifier = Modifier
                                .align(alignment = Alignment.CenterVertically)
                                .padding(top = 2.dp, start = 3.dp),
                            painter = painterResource(id = R.drawable.ic_baseline_error_outline_24),
                            contentDescription = "error"
                        )
                    }
                    Text(
                        "View details",
                        color = color,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.padding(all = 3.dp))
                Text(
                    "Extended Warranty (Monthly)",
                    color = color,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(all = 3.dp))
                Text(
                    "Warranty Expires : ${device.warrantyExpiryDate}",
                    fontSize = 18.sp,
                    color = Color(0Xff999999)
                )
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Column(modifier = Modifier.padding(horizontal = marginHorizontal)) {
                Text("How can we help you?", fontSize = 16.sp)
                Spacer(modifier = Modifier.padding(bottom = 16.dp))
                ButtonLayout(
                    "Request a repair service",
                    color,
                ) {
                    navController.navigate(AppScreens.RequestRepairServiceScreen.route)
                    println("Request a repair servicebutton clicked")
                }
                ButtonLayout(
                    "Check trade in offers",
                    color,
                ) {
                    navController.popBackStack()
                    println("Check trade button clicked")
                }
                ButtonLayout(
                    "View device details",
                    color,
                ) {
                    navController.popBackStack()
                    println("View device button clicked")
                }
            }
        }
    }
}

@Composable
fun ButtonLayout(btnText: String, buttonTextColor: Color, onClick: () -> Unit) {
    Column() {
        Button(
            modifier = Modifier.fillMaxWidth(1f),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0X55D4D4D4)),
            border = BorderStroke(width = 1.dp, color = Color.Transparent),
        ) {
            Text(
                btnText,
                color = buttonTextColor,
                modifier = Modifier.padding(8.dp),
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.sp
            )
        }
        Spacer(modifier = Modifier.padding(top = 12.dp))
    }

}
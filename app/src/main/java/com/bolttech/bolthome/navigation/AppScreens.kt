package com.bolttech.bolthome.navigation

sealed class AppScreens(val route: String) {
    object DashBoard : AppScreens("dashboard_screen")
    object AddDevicePreWifiScreen : AppScreens("add_device_pre_wifi_screen")
    object AddDevicePreWifiScreenWebview : AppScreens("add_device_pre_wifi_screen_web_view")
    object AddDeviceWifiSelectionScreen : AppScreens("add_device_wifi_selection_screen")
    object DeviceDetailScreen : AppScreens("device_detail_screen")
}

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bolttech.bolthome.navigation.AppScreens
import com.bolttech.bolthome.screens.AddDevicePreWifiScreen
import com.bolttech.bolthome.screens.AddDeviceWifiSelectionScreen
import com.bolttech.bolthome.screens.DashBoardScreen
import com.bolttech.bolthome.screens.DeviceDetailScreen

@Composable
fun Start() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.DashBoard.route) {
        composable(route = AppScreens.DashBoard.route) {
            DashBoardScreen(navController = navController)
        }
        composable(
            route = AppScreens.AddDevicePreWifiScreen.route,
        ) {
            AddDevicePreWifiScreen(navController)
        }
        composable(
            route = AppScreens.AddDeviceWifiSelectionScreen.route,
        ) {
            AddDeviceWifiSelectionScreen(navController)
        }
        composable(
            route = AppScreens.DeviceDetailScreen.route+ "/{pos}",
        ) {navBackStackEntry->
            val pos:String? = navBackStackEntry.arguments?.getString("pos");
            DeviceDetailScreen(navController, pos)
        }
    }
}
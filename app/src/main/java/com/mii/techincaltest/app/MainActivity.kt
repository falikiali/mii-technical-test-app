package com.mii.techincaltest.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mii.techincaltest.app.component.BarcodeScanner
import com.mii.techincaltest.app.helper.Screens
import com.mii.techincaltest.app.presentation.ConfirmPaymentScreen
import com.mii.techincaltest.app.presentation.MainScreen
import com.mii.techincaltest.app.ui.theme.MIITechnicalTestAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var barcodeScanner: BarcodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        barcodeScanner = BarcodeScanner(this)

        setContent {
            val navController = rememberNavController()

            MIITechnicalTestAppTheme {
                Surface {
                    NavHost(
                        navController = navController,
                        startDestination = Screens.Main.route
                    ) {
                        composable(
                            route = Screens.Main.route
                        )
                        {
                            MainScreen(navController = navController, barcodeScanner = barcodeScanner)
                        }

                        composable(
                            route = Screens.ConfirmPayment.route
                        ) {
                            ConfirmPaymentScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

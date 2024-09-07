package com.mii.techincaltest.app.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mii.techincaltest.app.helper.Screens
import com.mii.techincaltest.app.presentation.home.HomeScreen
import com.mii.techincaltest.app.presentation.portofolio.PortofolioScreen
import com.mii.techincaltest.app.presentation.promo.PromoScreen

@Composable
fun BottomNavigationGraph(
    bottomNavController: NavHostController,
    navController: NavHostController,
    modifier: Modifier,
    barcodeScanner: BarcodeScanner
) {
//
    NavHost(navController = bottomNavController, startDestination = Screens.Home.route) {
        composable(route = Screens.Home.route) {
            HomeScreen(
                modifier = modifier,
                navController = navController,
                barcodeScanner
            )
        }

        composable(route = Screens.Promo.route) {
            PromoScreen(
                modifier = modifier,
                navController = navController
            )
        }

        composable(route = Screens.Portofolio.route) {
            PortofolioScreen(
                modifier = modifier,
                navController = navController
            )
        }
    }
}
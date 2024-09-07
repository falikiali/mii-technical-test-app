package com.mii.techincaltest.app.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mii.techincaltest.app.component.BarcodeScanner
import com.mii.techincaltest.app.component.BottomNavigationGraph
import com.mii.techincaltest.app.component.BottomNavigationBar

@Composable
fun MainScreen(navController: NavHostController, barcodeScanner: BarcodeScanner) {
    val bottomNavigationController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = bottomNavigationController) }
    ) { paddingValues ->
        BottomNavigationGraph(
            bottomNavController = bottomNavigationController,
            navController = navController,
            modifier = Modifier
                .padding(paddingValues),
            barcodeScanner = barcodeScanner
        )
    }
}
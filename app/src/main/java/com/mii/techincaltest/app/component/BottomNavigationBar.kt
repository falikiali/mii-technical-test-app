package com.mii.techincaltest.app.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AreaChart
import androidx.compose.material.icons.filled.Discount
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AreaChart
import androidx.compose.material.icons.outlined.Discount
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mii.techincaltest.app.domain.model.ItemBottomNavigationBar

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val listItemBottomNavigationBar = listOf(
        ItemBottomNavigationBar(
            route = "home",
            title = "Home",
            iconSelected = Icons.Filled.Home,
            iconUnselected = Icons.Outlined.Home
        ),
        ItemBottomNavigationBar(
            route = "promo",
            title = "Promo",
            iconSelected = Icons.Filled.Discount,
            iconUnselected = Icons.Outlined.Discount
        ),
        ItemBottomNavigationBar(
            route = "portofolio",
            title = "Portofolio",
            iconSelected = Icons.Filled.AreaChart,
            iconUnselected = Icons.Outlined.AreaChart
        )
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        listItemBottomNavigationBar.forEach { item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    if (currentDestination?.hierarchy?.any { it.route == item.route } == false) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (currentDestination?.hierarchy?.any { it.route == item.route } == true) item.iconSelected else item.iconUnselected,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(
                        text = item.title
                    )
                },
                alwaysShowLabel = false,
            )
        }
    }
}
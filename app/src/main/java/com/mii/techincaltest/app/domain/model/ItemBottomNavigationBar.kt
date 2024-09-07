package com.mii.techincaltest.app.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class ItemBottomNavigationBar(
    val route: String,
    val title: String,
    val iconSelected: ImageVector,
    val iconUnselected: ImageVector
)

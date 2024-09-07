package com.mii.techincaltest.app.presentation.promo

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun PromoScreen(
    modifier: Modifier,
    navController: NavHostController
) {
    Text(
        modifier = modifier,
        text = "Promo Screen",
        fontSize = 16.sp,
        fontStyle = FontStyle.Italic,
        textDecoration = TextDecoration.LineThrough
    )
}
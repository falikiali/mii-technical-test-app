package com.mii.techincaltest.app.helper

sealed class Screens(val route: String) {
    object Home: Screens("home")
    object Promo: Screens("promo")
    object Portofolio: Screens("portofolio")

    object Main: Screens("main")
    object ConfirmPayment: Screens("confirm payment")
    object DetailPromo: Screens("detail promo")
    object DetailPortofolio: Screens("detail portofolio")
}
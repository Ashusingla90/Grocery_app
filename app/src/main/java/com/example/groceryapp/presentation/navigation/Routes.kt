package com.example.groceryapp.presentation.navigation

sealed class Screens(val route: String) {

    object PhoneAuthScreen : Screens("phone_auth")

    object HomeScreen : Screens("home")

    object CategoryScreen : Screens("category")

    object OrderAgainScreen : Screens("order_again")

    object PrintScreen : Screens("print")

    object SearchBarScreen : Screens("search_screen")

    object ProfileScreen : Screens("profile")

    object ProductScreen : Screens("product_screen")

    object VerticalTabProductsScreen : Screens("vertical_tab_products")

    object FinalCheckOutScreen : Screens("final_checkout_screen")
}

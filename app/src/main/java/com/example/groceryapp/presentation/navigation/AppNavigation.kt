package com.example.groceryapp.presentation.navigation


import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.groceryapp.presentation.CartViewModel
import com.example.groceryapp.presentation.screens.BlinkItProfileScreen
import com.example.groceryapp.presentation.screens.CategoryScreen
import com.example.groceryapp.presentation.screens.HomeScreen
import com.example.groceryapp.presentation.screens.OrderAgainScreen
import com.example.groceryapp.presentation.screens.PhoneAuthScreen
import com.example.groceryapp.presentation.screens.ProductScreen
import com.example.groceryapp.presentation.screens.SearchBarScreen
import com.example.groceryapp.presentation.screens.VerticalTabProductsScreen


import com.example.groceryapp.presentation.screens.*

@Composable
fun NavigationGraph(
    navController: NavHostController,
    listState: LazyListState,
    cartViewModel: CartViewModel = hiltViewModel()
) {


    NavHost(
        navController = navController,
        startDestination = Screens.PhoneAuthScreen.route
    ) {

        // Auth Flow
        composable(Screens.PhoneAuthScreen.route) {
            PhoneAuthScreen(
                navHostController = navController,
                listState = listState
            )
        }

        // Main App Screens
        composable(Screens.HomeScreen.route) {
            HomeScreen(navHostController = navController, listState = listState)
        }

        composable(Screens.OrderAgainScreen.route) {
            OrderAgainScreen(
                navController,
                listState = listState,
                cartViewModel
            )
        }

        composable(Screens.CategoryScreen.route) {
            CategoryScreen(
                navController,
                listState = listState
            )
        }

        composable(Screens.PrintScreen.route) {
            PrintScreen(
                navController,
                listState = listState
            )
        }

        composable(Screens.SearchBarScreen.route) {
            SearchBarScreen(navController)
        }

        composable(Screens.ProfileScreen.route) {
            BlinkItProfileScreen(navController)
        }

        composable(Screens.ProductScreen.route) {
            ProductScreen(navController, cartViewModel, "20% OFF")
        }

        composable(Screens.VerticalTabProductsScreen.route) {
            VerticalTabProductsScreen(navController, cartViewModel)
        }

        composable(Screens.FinalCheckOutScreen.route) {
            FinalCheckOutScreen(navController, cartViewModel)
        }
    }
}



package com.example.groceryapp.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import android.widget.Toast
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.groceryapp.PhoneAuthViewModel
import com.example.groceryapp.common.AuthState
import com.example.groceryapp.presentation.navigation.Screens

@Composable
fun PhoneAuthScreen(
    viewModel: PhoneAuthViewModel = hiltViewModel(),
    navHostController: NavHostController,
    listState: LazyListState
) {

    val authState by viewModel.authState.collectAsState()
    val context = LocalContext.current

    // 🔁 React to state changes
    LaunchedEffect(authState) {
        when (authState) {

            is AuthState.Error -> {
                Toast.makeText(
                    context,
                    (authState as AuthState.Error).message,
                    Toast.LENGTH_LONG
                ).show()
            }

            is AuthState.Verified -> {
                Toast.makeText(
                    context,
                    "Authentication successful!",
                    Toast.LENGTH_LONG
                ).show()

                navHostController.navigate(Screens.HomeScreen.route) {
                    popUpTo(0)
                }
            }

            else -> {}
        }
    }

    // 🔀 Switch UI based on state
    when (authState) {

        is AuthState.Initial -> {
            PhoneNumberInputScreen(
                viewModel = viewModel,
                navController = navHostController
            )
        }

        is AuthState.CodeSent -> {
            OtpVerificationScreen(
                viewModel = viewModel
            )
        }

        else -> {}
    }
}

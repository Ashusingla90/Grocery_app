package com.example.groceryapp.presentation.utils



import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.groceryapp.R
import com.example.groceryapp.presentation.CartViewModel
import com.example.groceryapp.presentation.navigation.NavigationGraph
import com.example.groceryapp.presentation.navigation.Screens

// ---------------- DATA CLASS ----------------

data class BottomNavItem(
    val title: String,
    val icon: Painter,
    val screen: Screens
)


// ---------------- MAIN COMPOSABLE ----------------

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNav(
    isVisible: Boolean,
    navController: NavHostController,
    listState: LazyListState,
    cartViewModel: CartViewModel = hiltViewModel()
) {

    val itemCount by cartViewModel.cartItemCount
    val showCartButton by cartViewModel.showCartButton

    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

    val bottomNavItems = listOf(
        BottomNavItem(
            title = "Home",
            icon = painterResource(R.drawable.home),
            screen = Screens.HomeScreen
        ),
        BottomNavItem(
            title = "Categories",
            icon = painterResource(R.drawable.category),
            screen = Screens.CategoryScreen
        ),
        BottomNavItem(
            title = "Cart",
            icon = painterResource(R.drawable.shopping_cart),
            screen = Screens.CategoryScreen
        ),
        BottomNavItem(
            title = "Profile",
            icon = painterResource(R.drawable.profile),
            screen = Screens.ProfileScreen
        )
    )

    Scaffold(

        bottomBar = {

            AnimatedVisibility(
                visible = isVisible,
                enter = slideInVertically { it } + fadeIn(),
                exit = slideOutVertically { it } + fadeOut()
            ) {

                NavigationBar {

                    bottomNavItems.forEachIndexed { index, item ->

                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                navController.navigate(item.screen.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    painter = item.icon,
                                    contentDescription = item.title
                                )
                            },
                            label = {
                                Text(text = item.title)
                            }
                        )
                    }
                }
            }
        },

        floatingActionButton = {

            if (showCartButton && itemCount > 0) {

                FloatingCartButton(
                    itemCount = itemCount,
                    onClick = {
                        navController.navigate(Screens.CategoryScreen.route)
                    }
                )
            }
        }

    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            NavigationGraph (
                navController = navController,
                listState = listState
            )
        }
    }
}

@Composable
fun FloatingCartButton(
    itemCount: Int,
    onClick: () -> Unit
) {

    ExtendedFloatingActionButton(
        onClick = onClick
    ) {
        Text(text = "View Cart ($itemCount)")
    }
}
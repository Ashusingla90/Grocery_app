package com.example.groceryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.groceryapp.presentation.CartViewModel
import com.example.groceryapp.ui.theme.GroceryAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import com.example.groceryapp.presentation.utils.BottomNav

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            val cartViewModel: CartViewModel = hiltViewModel()

            // Track scroll direction
            var isVisible by remember { mutableStateOf(true) }
            var lastScrollOffset by remember { mutableStateOf(0) }
            var lastIndex by remember { mutableStateOf(0) }

            val listState = rememberLazyListState()

            // Listen to scroll events
            LaunchedEffect(listState) {

                snapshotFlow {
                    listState.firstVisibleItemIndex to
                            listState.firstVisibleItemScrollOffset
                }
                    .distinctUntilChanged()
                    .collectLatest { (index, scrollOffset) ->

                        if (index > lastIndex ||
                            (index == lastIndex &&
                                    scrollOffset > lastScrollOffset + 50)
                        ) {
                            // Scrolling Down → Hide BottomNavBar
                            isVisible = false
                        } else if (index < lastIndex ||
                            (scrollOffset < lastScrollOffset - 50)
                        ) {
                            // Scrolling Up → Show BottomNavBar
                            isVisible = true
                        }

                        lastIndex = index
                        lastScrollOffset = scrollOffset
                    }
            }

            val navController = rememberNavController()

            GroceryAppTheme {

                BottomNav(
                    isVisible = isVisible,
                    navController = navController,
                    listState = listState,
                    cartViewModel = cartViewModel
                )
            }
        }
    }
}



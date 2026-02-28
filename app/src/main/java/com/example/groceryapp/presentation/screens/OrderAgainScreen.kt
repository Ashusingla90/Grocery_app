package com.example.groceryapp.presentation.screens


import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.groceryapp.R
import com.example.groceryapp.presentation.CartViewModel
import com.example.groceryapp.presentation.component.BlinkitSearchBar
import com.example.groceryapp.presentation.component.ProductCard
import com.example.groceryapp.presentation.component.ProductItem

@Composable
fun OrderAgainScreen(
    navController: NavHostController,
    listState: LazyListState,
    cartViewModel: CartViewModel = hiltViewModel()
) {

    /* --------------------------------------------------- */
    /*  Scroll Animations                                 */
    /* --------------------------------------------------- */

    val firstVisibleItemOffset by remember {
        derivedStateOf { listState.firstVisibleItemScrollOffset }
    }

    val firstVisibleItemIndex by remember {
        derivedStateOf { listState.firstVisibleItemIndex }
    }

    val isScrolled by remember {
        derivedStateOf {
            firstVisibleItemIndex > 0 || firstVisibleItemOffset > 0
        }
    }

    val headerHeight by animateDpAsState(
        targetValue = if (isScrolled) 0.dp else 88.dp,
        animationSpec = tween(500, easing = FastOutSlowInEasing),
        label = "headerHeight"
    )

    val scrollOffset = remember {
        derivedStateOf {
            (firstVisibleItemIndex * 80 + firstVisibleItemOffset)
                .coerceAtMost(80)
        }
    }

    val topContentOffset by animateDpAsState(
        targetValue = -scrollOffset.value.dp,
        animationSpec = tween(500, easing = FastOutSlowInEasing),
        label = "topContentOffset"
    )

    /* --------------------------------------------------- */
    /*  Dummy Products                                    */
    /* --------------------------------------------------- */

    val productItems = listOf(
        ProductItem("Kitchen Appliances", "100 g", "20% OFF", "₹39", "₹49", R.drawable.kitchen, "11 MINS", listOf("Fiber Rich"), 0),
        ProductItem(
            "Begun",
            "3 pieces",
            "23% OFF",
            "₹39",
            "₹51",
            R.drawable.began,
            "11 MINS",
            listOf("Energy Booster"),
            19
        ),
        ProductItem("Cabbage", "100 g", "20% OFF", "₹39", "₹49", R.drawable.cabbage, "11 MINS", emptyList(), 0),
        ProductItem("Carrot", "3 pieces", "23% OFF", "₹39", "₹51", R.drawable.carrot, "11 MINS", listOf("Energy Booster"), 19),
        ProductItem("Garlic", "100 g", "20% OFF", "₹39", "₹49", R.drawable.garlic, "11 MINS", emptyList(), 0),
        ProductItem("Chocolate", "3 pieces", "23% OFF", "₹39", "₹51", R.drawable.chocolate, "11 MINS", listOf("Energy Booster"), 19),
        ProductItem("Dry Fruits", "100 g", "20% OFF", "₹349", "₹449", R.drawable.dryfruits, "11 MINS", emptyList(), 0),
        ProductItem("Milk", "100 g", "20% OFF", "₹39", "₹49", R.drawable.milk, "11 MINS", emptyList(), 0),
        ProductItem("Maggie", "100 g", "20% OFF", "₹39", "₹49", R.drawable.instantfood, "11 MINS", emptyList(), 0),
        ProductItem("Tea", "100 gm", "23% OFF", "₹129", "₹151", R.drawable.tea, "11 MINS", listOf("Energy Booster"), 19),
        ProductItem("Fruit Basket", "3 pieces", "23% OFF", "₹39", "₹51", R.drawable.fruitsandvegetables, "11 MINS", listOf("Energy Booster"), 19),
    )

    /* --------------------------------------------------- */
    /*  Scaffold                                           */
    /* --------------------------------------------------- */

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0xFFFFB61E),
                                Color(0xFFFFAD64)
                            )
                        )
                    )
                    .padding(horizontal = 8.dp)
            ) {

                Column(
                    modifier = Modifier
                        .offset(y = topContentOffset)
                        .height(headerHeight)
                        .padding(vertical = 4.dp)
                ) {

                    // Delivery Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Column {
                            Text(
                                text = "Delivery in",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black.copy(alpha = 0.7f)
                            )

                            Text(
                                text = "10 minutes",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }

                        // Profile Icon
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color.Black.copy(0.5f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Profile",
                                tint = Color.White
                            )
                        }
                    }

                    // Location Row
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 2.dp, bottom = 8.dp)
                    ) {
                        Text(
                            text = "Biharipura, Vijay Nagar, Bhim Nagar, Vijay",
                            fontSize = 14.sp,
                            color = Color.Black.copy(0.8f),
                            maxLines = 1
                        )

                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Expand",
                            tint = Color.Black
                        )
                    }

                    BlinkitSearchBar(navController)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    ) { innerPadding ->

        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            item {
                Text(
                    text = "Bestsellers",
                    modifier = Modifier.padding(10.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray
                )
            }

            items(productItems.chunked(3)) { rowItems ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {

                    rowItems.forEach { product ->
                        ProductCard(
                            product = product,
                            modifier = Modifier.weight(1f),
                            navController = navController,
                            cartViewModel = cartViewModel
                        )
                    }

                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = colorResource(R.color.gray),
                    thickness = 1.5.dp
                )
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

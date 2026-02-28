package com.example.groceryapp.presentation.screens


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.groceryapp.R
import com.example.groceryapp.presentation.CartViewModel
import com.example.groceryapp.presentation.component.ProductCard
import com.example.groceryapp.presentation.component.ProductItem
import com.example.groceryapp.presentation.CartViewModel as CartViewModel1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    navController: NavHostController,
    cartViewModel: CartViewModel,
    offer: String
) {

    val lazyListState = rememberLazyListState()

    var productName by remember { mutableStateOf("Hydroponic Sweet Bell Pepper") }
    var quantity by remember { mutableStateOf("2 pcs (200–350 g)") }
    var price by remember { mutableStateOf("₹170") }
    var mrp by remember { mutableStateOf("₹250") }
    var deliveryTime by remember { mutableStateOf("10 MINS") }

    val showTitle by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 0 ||
                    lazyListState.firstVisibleItemScrollOffset > 150
        }
    }

    val topBarAlpha by animateFloatAsState(
        targetValue = if (showTitle) 1f else 0f,
        animationSpec = tween(500),
        label = ""
    )

    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = productName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.alpha(topBarAlpha)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painterResource(id = R.drawable.down_arrow),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            painterResource(id = R.drawable.searchicon),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(onClick = {}) {
                        Icon(
                            painterResource(id = R.drawable.share_square),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White.copy(alpha = topBarAlpha)
                )
            )
        },

        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {
                    Text(quantity, fontSize = 14.sp, fontWeight = FontWeight.Bold)

                    Row {
                        Text(price, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            mrp,
                            fontSize = 16.sp,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }

                    Text("inclusive all taxes", fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Add to cart", fontSize = 16.sp)
                }
            }
        }

    ) { innerPadding ->

        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            item {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.spring_roll),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        ProductDetailCard(
                            productName = productName,
                            quantity = quantity,
                            price = price,
                            mrp = mrp,
                            offer = offer,
                            deliveryTime = deliveryTime
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        PeopleAlsoBoughtCard(
                            navController = navController,
                            cartViewModel = cartViewModel
                        )

                        Spacer(modifier = Modifier.height(56.dp))

                    }
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = "India's last",
                        fontSize = 60.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Minute app",
                            fontSize = 60.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.heart),
                            contentDescription = "heart",
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .size(60.dp),
                            tint = Color.Unspecified
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                        color = colorResource(id = R.color.gray),
                        thickness = 1.5.dp
                    )
                    Text(
                        text = "Grocers",
                        modifier = Modifier.padding(horizontal = 8.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }


        }
    }
}


@Composable
fun ProductDetailCard(
    productName: String,
    quantity: String,
    price: String,
    mrp: String,
    offer: String,
    deliveryTime: String
) {
    // First Card: Delivery Time
    Card(
        modifier = Modifier.width(92.dp),
        shape = RoundedCornerShape(
            topStart = 12.dp,
            topEnd = 12.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        ),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.timer),
                tint = colorResource(R.color.green),
                modifier = Modifier.size(14.dp),
                contentDescription = "timer"
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = deliveryTime,
                fontSize = 14.sp,
                color = Color.DarkGray,
                fontWeight = FontWeight.SemiBold
            )
        }
    }

    // Second Card: Product Info and Pricing
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 12.dp,
            bottomStart = 12.dp,
            bottomEnd = 12.dp
        ),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = productName,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = quantity,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = price,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "MRP",
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = mrp,
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    textDecoration = TextDecoration.LineThrough
                )
                Spacer(modifier = Modifier.width(6.dp))

                // Offer Tag
                Box(
                    modifier = Modifier
                        .background(
                            color = Color.Blue.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(4.dp)
                        )
                ) {
                    Text(
                        text = offer,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(4.dp),
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Blue
                    )
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // View Product Details Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "View Product details",
                    fontSize = 16.sp,
                    color = colorResource(R.color.ViewActivityClickableColor),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    painter = painterResource(R.drawable.arrowdown),
                    modifier = Modifier.size(30.dp),
                    tint = colorResource(R.color.ViewActivityClickableColor),
                    contentDescription = "detail Arrow"
                )
            }
        }
    }
}


@Composable
fun PeopleAlsoBoughtCard(
    navController: NavHostController,
    cartViewModel: CartViewModel1
) {
    val productItems = listOf(
        ProductItem(
            name = "Pooja Flower Mix",
            quantity = "100 g",
            discountPercentage = "20% OFF",
            price = "₹39",
            mrp = "₹49",
            imageRes = R.drawable.pooja_mix,
            deliveryTime = "11 MINS",
            tags = emptyList(),
            recipeCount = 0
        ),
        // Repetitive items as shown in the screenshots (Banana example)
        ProductItem(
            name = "Banana",
            quantity = "3 pieces",
            discountPercentage = "23% OFF",
            price = "₹39",
            mrp = "₹51",
            imageRes = R.drawable.banana,
            deliveryTime = "11 MINS",
            tags = emptyList(),
            recipeCount = 19
        )
        // ... Add more items to fill the list
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "People also bought",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(12.dp)
            )

            LazyVerticalGrid(
                userScrollEnabled = false, // Controlled by parent scroll
                columns = GridCells.Fixed(3), // 3 items per row
                contentPadding = PaddingValues(horizontal = 4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1010.dp) // Fixed height to accommodate 3xN grid
            ) {
                items(productItems) { products ->
                    ProductCard(
                        product = products,
                        navController = navController,
                        cartViewModel = cartViewModel
                    )
                }
            }
        }
    }
}
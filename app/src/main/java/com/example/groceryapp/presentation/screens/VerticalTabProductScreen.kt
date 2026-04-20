package com.example.groceryapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.groceryapp.R
import com.example.groceryapp.presentation.CartViewModel
import com.example.groceryapp.presentation.component.FiltersRow

data class Category(
    val name: String,
    val iconRes: Int
)

data class ProductItem(
    val name: String,
    val quantity: String,
    val discountPercentage: String,
    val price: String,
    val mrp: String,
    val imageRes: Int,
    val deliveryTime: String,
    val tags: List<String>,
    val recipeCount: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerticalTabProductsScreen(
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel()
) {

    val categories = listOf(
        Category("All", R.drawable.ic_all),
        Category("Fresh Vegetables", R.drawable.vegetable),
        Category("Fresh Fruits", R.drawable.fruits),
        Category("Biscuits", R.drawable.biscuit),
        Category("Coriander & Others", R.drawable.coriander),
        Category("Flowers & Leaves", R.drawable.flower),
        Category("Seasonal", R.drawable.season),
        Category("Freshly Cut & Sprouts", R.drawable.sprouts)
    )

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
        ProductItem(
            name = "Banana",
            quantity = "3 pieces",
            discountPercentage = "23% OFF",
            price = "₹39",
            mrp = "₹51",
            imageRes = R.drawable.banana,
            deliveryTime = "11 MINS",
            tags = listOf("Energy Booster"),
            recipeCount = 19
        )
    )

    val filters = listOf("Filter", "Tomato", "Apple", "Kiwi", "Vegetables")
    var selectedCategory by remember { mutableStateOf(categories[0]) }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = { Text("Vegetables & Fruits") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(Color.White)
            )
        }
    ) { innerPadding ->

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            // LEFT SIDEBAR
            CategorySidebar(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it }
            )

            // MAIN CONTENT
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {

                FiltersRow(filters)

                ProductGrid(
                    products = productItems,
                    navController = navController as NavHostController,
                    cartViewModel = cartViewModel
                )
            }
        }
    }
}

@Composable
fun CategorySidebar(
    categories: List<Category>,
    selectedCategory: Category,
    onCategorySelected: (Category) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .width(80.dp)
            .fillMaxHeight()
            .background(Color.White)
    ) {
        items(categories) { category ->
            CategoryItem(
                category = category,
                isSelected = category == selectedCategory,
                onClick = { onCategorySelected(category) }
            )
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF0F0F0)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = category.iconRes),
                    contentDescription = category.name,
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = category.name,
                fontSize = 10.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                maxLines = 2,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
        }

        if (isSelected) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(70.dp)
                    .background(
                        Color(0xFF2E7D32),
                        RoundedCornerShape(topStart = 50.dp, bottomStart = 50.dp)
                    )
            )
        } else {
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}

@Composable
fun ProductGrid(
    products: List<ProductItem>,
    navController: NavHostController,
    cartViewModel: CartViewModel
) {

    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(products.chunked(2)) { rowItems ->

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {

                rowItems.forEach { product ->
                    ProductCard(
                        product = product,
                        modifier = Modifier.weight(1f)
                    )
                }

                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}


@Composable
fun ProductCard(
    product: ProductItem,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(2.dp)
    ) {

        Column(modifier = Modifier.padding(8.dp)) {

            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(product.name, fontWeight = FontWeight.Bold)
            Text(product.quantity, fontSize = 12.sp, color = Color.Gray)
            Text(product.price, fontWeight = FontWeight.Bold)
        }
    }
}
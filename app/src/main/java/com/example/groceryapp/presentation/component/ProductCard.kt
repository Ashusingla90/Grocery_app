package com.example.groceryapp.presentation.component


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.groceryapp.R
import com.example.groceryapp.presentation.CartViewModel
import com.example.groceryapp.presentation.navigation.Screens

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


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProductCard(
    product: ProductItem,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    cartViewModel: CartViewModel = hiltViewModel()
) {

    val itemCount by cartViewModel.cartItemCount

    Card(
        modifier = modifier
            .fillMaxHeight()
            .width(150.dp)
            .padding(vertical = 4.dp)
            .clickable {
                navController.navigate(Screens.ProductScreen.route)
            },
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(10.dp)
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            // Product Image Section
            Box(
                modifier = Modifier.size(150.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.name,
                    modifier = Modifier
                        .size(110.dp)
                        .clip(RoundedCornerShape(12.dp))
                )

                // ADD Button Section
                if (itemCount == 0) {
                    Button(
                        onClick = { cartViewModel.addItem() },
                        modifier = Modifier
                            .size(width = 75.dp, height = 35.dp)
                            .align(Alignment.BottomEnd),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color(0xFF4CAF50)
                        ),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, Color(0xFF4CAF50))
                    ) {
                        Text(
                            text = "ADD",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF238829)
                        )
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .size(width = 75.dp, height = 35.dp)
                            .align(Alignment.BottomEnd)
                            .background(
                                color = colorResource(R.color.green),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(horizontal = 6.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Icon(
                            painter = painterResource(R.drawable.outline_check_indeterminate_small_24),
                            contentDescription = null,
                            modifier = Modifier
                                .size(18.dp)
                                .clickable { cartViewModel.decreaseItem() },
                            tint = Color.White
                        )

                        Text(
                            text = itemCount.toString(),
                            fontSize = 14.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        Icon(
                            painter = painterResource(R.drawable.baseline_add_24),
                            contentDescription = null,
                            modifier = Modifier
                                .size(18.dp)
                                .clickable { cartViewModel.addItem() },
                            tint = Color.White
                        )
                    }
                }
            }

            // Product Details Section
            Column(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            ) {

                // Quantity Tag
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFFEDF7E))
                    ) {
                        Text(
                            text = product.quantity,
                            modifier = Modifier.padding(horizontal = 4.dp),
                            fontSize = 8.sp,
                            color = Color(0xFF4A0244)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Tags
                if (product.tags.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .background(Color(0xFFFEDF7E))
                    ) {
                        Text(
                            text = product.tags[0],
                            modifier = Modifier.padding(horizontal = 4.dp),
                            fontSize = 8.sp,
                            color = Color(0xFF4A0244)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Product Name
                Text(
                    text = product.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Delivery Time
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Icon(
                        painter = painterResource(id = R.drawable.baseline_add_24),
                        contentDescription = null,
                        modifier = Modifier.size(12.dp),
                        tint = Color(0xFF4CAF50)
                    )

                    Text(
                        text = product.deliveryTime,
                        fontSize = 10.sp,
                        color = Color(0xFF4CAF50),
                        modifier = Modifier.padding(start = 2.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Discount
                if (product.discountPercentage.isNotEmpty()) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = product.discountPercentage,
                            color = Color.Blue,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Price Section
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(
                        text = product.price,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = " MRP ${product.mrp}",
                        color = Color.Gray,
                        fontSize = 10.sp,
                        textDecoration = TextDecoration.LineThrough,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Recipes Section
                if (product.recipeCount > 0) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(28.dp)
                            .background(Color(0xFFFEDF7E)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "See ${product.recipeCount} recipes",
                            modifier = Modifier.padding(start = 6.dp),
                            fontSize = 10.sp,
                            color = Color(0xFF1C801F)
                        )
                    }
                }
            }
        }
    }
}

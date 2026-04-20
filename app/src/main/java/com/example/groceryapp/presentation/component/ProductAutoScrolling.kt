package com.example.groceryapp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.groceryapp.R
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlin.math.ceil

data class ProductItemAutoScroll(
    val imageRes: Int,
    val name: String
)

@Composable
fun AutoScrollingProductCarousel(navController: NavHostController) {

    val products = listOf(
        ProductItemAutoScroll(R.drawable.milk, ""),
        ProductItemAutoScroll(R.drawable.tea, ""),
        ProductItemAutoScroll(R.drawable.kitkat, ""),
        ProductItemAutoScroll(R.drawable.choclate, ""),
        ProductItemAutoScroll(R.drawable.dryfruits, ""),
        ProductItemAutoScroll(R.drawable.potato, ""),
        ProductItemAutoScroll(R.drawable.brocli, ""),
        ProductItemAutoScroll(R.drawable.fruitsandvegetables, ""),
        ProductItemAutoScroll(R.drawable.instantfood, "")
    )

    val scrollSpeed = 1.5f
    val spacing = 8.dp

    AutoScrollingHorizontalColumn(
        products = products,
        speed = scrollSpeed,
        spacing = spacing,
        navController = navController
    )
}
@Composable
fun AutoScrollingHorizontalColumn(
    products: List<ProductItemAutoScroll>,
    speed: Float,
    spacing: Dp,
    navController: NavHostController
) {

    val scrollOffset = remember { mutableFloatStateOf(0f) }

    val itemWidth = 100.dp
    val density = LocalDensity.current

    val itemWidthPx = with(density) { itemWidth.toPx() }
    val spacingPx = with(density) { spacing.toPx() }
    val totalItemWidthPx = itemWidthPx + spacingPx

    val repeatedProducts = remember {
        List(5) { products }.flatten()
    }

    val itemsPerColumn = 3
    val screenWidthPx =
        with(density) { LocalConfiguration.current.screenWidthDp.dp.toPx() }

    val totalContentWidth =
        repeatedProducts.size * totalItemWidthPx / itemsPerColumn

    LaunchedEffect(Unit) {
        while (true) {
            delay(16) // ~60fps
            scrollOffset.floatValue += speed

            if (scrollOffset.floatValue >
                totalContentWidth - screenWidthPx
            ) {
                scrollOffset.floatValue =
                    -(products.size * totalItemWidthPx / itemsPerColumn)
            }
        }
    }

    Box(modifier = Modifier.fillMaxWidth()) {

        val columnCount =
            ceil(repeatedProducts.size.toFloat() / itemsPerColumn).toInt()

        for (col in 0 until columnCount) {

            val xOffset =
                -scrollOffset.floatValue + col * totalItemWidthPx

            if (xOffset > -totalItemWidthPx * 2 &&
                xOffset < screenWidthPx + totalItemWidthPx * 2
            ) {

                Column(
                    modifier = Modifier
                        .graphicsLayer { translationX = xOffset }
                        .width(itemWidth),
                    verticalArrangement = Arrangement.spacedBy(spacing)
                ) {

                    for (row in 0 until itemsPerColumn) {

                        val index =
                            (col * itemsPerColumn + row) %
                                    repeatedProducts.size

                        ProductCardForAutoScroll(
                            repeatedProducts[index]
                        )
                    }
                }
            }
        }

        Button(
            onClick = {
                navController.navigate("home") {
                    launchSingleTop = true
                }
            },
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 12.dp)
                .align(Alignment.TopEnd)
                .clip(CircleShape)
                .size(width = 80.dp, height = 35.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White.copy(alpha = 0.8f),
                contentColor = Color.DarkGray
            ),
            shape = CircleShape
        ) {
            Text(text = "Skip")
        }
    }
}
@Composable
fun ProductCardForAutoScroll(
    product: ProductItemAutoScroll
) {

    Card(
        modifier = Modifier
            .padding(2.dp)
            .size(100.dp),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(
            containerColor =
                colorResource(id = R.color.simpleProductColor)
        )
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}

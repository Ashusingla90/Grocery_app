package com.example.groceryapp.presentation.component


import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

// -----------------------------
// Data Class
// -----------------------------
data class BestSellerData(
    val title: String,
    val imageResids: List<Int>,
    val label: String
)

// -----------------------------
// Main Component
// -----------------------------
@Composable
fun BestSellerComponent(
    works: BestSellerData,
    navController: NavHostController
) {

    Card(
        modifier = Modifier
            .size(width = 120.dp, height = 180.dp)
            .padding(horizontal = 6.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray.copy(alpha = 0.3f)
        )
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .padding(4.dp)
                .clickable {
//                    navController.navigate(Screens.VerticalTabProductsScreen.route)
                }
        ) {

            // -------- Image 1 --------
            works.imageResids.getOrNull(0)?.let {
                Box(
                    modifier = Modifier
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .size(50.dp)
                        .align(Alignment.TopStart)
                ) {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = "Milk",
                        modifier = Modifier
                            .size(44.dp)
                            .align(Alignment.Center)
                    )
                }
            }

            // -------- Image 2 --------
            works.imageResids.getOrNull(1)?.let {
                Box(
                    modifier = Modifier
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .size(50.dp)
                        .align(Alignment.TopEnd)
                ) {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = "Milk",
                        modifier = Modifier
                            .size(44.dp)
                            .align(Alignment.Center)
                    )
                }
            }

            // -------- Image 3 --------
            works.imageResids.getOrNull(2)?.let {
                Box(
                    modifier = Modifier
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .size(50.dp)
                        .align(Alignment.CenterStart)
                ) {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = "Milk",
                        modifier = Modifier
                            .size(44.dp)
                            .align(Alignment.Center)
                    )
                }
            }

            // -------- Image 4 --------
            works.imageResids.getOrNull(3)?.let {
                Box(
                    modifier = Modifier
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .size(50.dp)
                        .align(Alignment.CenterEnd)
                ) {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = "Milk",
                        modifier = Modifier
                            .size(44.dp)
                            .align(Alignment.Center)
                    )
                }
            }

            // -------- Title --------
            Text(
                text = works.title,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                lineHeight = 18.sp,
                textAlign = TextAlign.Center
            )

            // -------- Label Card --------
            Card(
                modifier = Modifier
                    .padding(top = 44.dp)
                    .background(
                        color = Color.LightGray.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .size(44.dp, 21.dp)
                    .align(Alignment.BottomCenter),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 1.dp
                )
            ) {
                Text(
                    text = works.label,
                    color = Color.Black,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(bottom = 5.dp, start = 2.dp)
                )
            }
        }
    }
}

// -----------------------------
// Preview
// -----------------------------
//@Preview(showBackground = true)
//@Composable
//fun BestSellerComponentPreview() {
//
//    BestSellerComponent(
//        works = BestSellerData(
//            title = "Top Picks",
//            imageResids = listOf(
//                android.R.drawable.ic_menu_camera,
//                android.R.drawable.ic_menu_gallery,
//                android.R.drawable.ic_menu_manage,
//                android.R.drawable.ic_menu_report_image
//            ),
//            label = "Popular"
//        ),
//        navController = rememberNavController()
//    )
//}

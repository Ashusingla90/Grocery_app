package com.example.groceryapp.presentation.component

import androidx.compose.runtime.Composable
import com.example.groceryapp.R
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class AartiData(
    val title: String,
    val pageCount: String,
    val reviews: Int,
    val discountPrice: Int,
    val originalPrice: Int,
    val imageRes: Int
)

@Composable
fun AartiUi() {

    val aartiList = listOf(
        AartiData("Om Jai Jagdish Hare", "3 pages", 22, 19, 38, R.drawable.wishnuji),
        AartiData("Laxmi Ji Ki Aarti", "3 pages", 31, 19, 38, R.drawable.laxmimata),
        AartiData("Ganesh Ji Ki Aarti", "3 pages", 24, 19, 38, R.drawable.ganeshji),
        AartiData("Ambe Ji Ki Aarti", "5 pages", 21, 19, 38, R.drawable.ambema),
        AartiData("Shri Ram Stuti", "4 pages", 30, 19, 38, R.drawable.ramji),
        AartiData("Saraswati Ji Ki Aarti", "3 pages", 42, 19, 38, R.drawable.saraswatimata)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F5E9))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Print Your Aartees",
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp
        )

        // Creating 2-column rows manually
        for (i in aartiList.indices step 2) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                AartiCard(data = aartiList[i])

                if (i + 1 < aartiList.size) {
                    AartiCard(data = aartiList[i + 1])
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
@Composable
fun AartiCard(data: AartiData) {

    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.width(180.dp)
    ) {

        Column(
            modifier = Modifier
                .background(Color(0xFFF8F5E9))
                .padding(8.dp)
        ) {

            Box {

                Image(
                    painter = painterResource(id = data.imageRes),
                    contentDescription = data.title,
                    modifier = Modifier
                        .height(120.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Button(
                    onClick = { /* Handle print */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Color(0xFF4CAF50)),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .height(38.dp)
                        .width(90.dp)
                ) {
                    Text(
                        text = "Print",
                        color = Color(0xFF4CAF50),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = data.pageCount,
                color = Color(0xFF1E3A8A),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = data.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(verticalAlignment = Alignment.CenterVertically) {

                repeat(4) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(14.dp)
                    )
                }

                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(14.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "(${data.reviews})",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = "50% OFF",
                    color = Color(0xFF1E8855),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "₹${data.discountPrice}",
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "MRP ₹${data.originalPrice}",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    style = TextStyle(textDecoration = TextDecoration.LineThrough)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArtiCardPrev() {
    AartiUi()
}


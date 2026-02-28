package com.example.groceryapp.presentation.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groceryapp.R

@Composable
fun MaggiNoodleItem(
    cardImage: Int,
    title: String
) {

    Column(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 10.dp)
            .width(115.dp)
    ) {

        Box(
            modifier = Modifier
                .background(
                    color = Color.LightGray.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(12.dp)
                )
                .size(180.dp, 110.dp)
        ) {

            // Bestseller Tag
            Box(
                modifier = Modifier
                    .background(
                        color = Color(0xFFEE8A0),
                        shape = RoundedCornerShape(bottomEnd = 5.dp, bottomStart = 5.dp)
                    )
                    .padding(horizontal = 10.dp)
                    .align(Alignment.TopCenter)
            ) {
                Text(
                    text = "Bestseller",
                    fontSize = 10.sp,
                    color = Color(0xFF7C6200)
                )
            }

            // Product Image
            Image(
                painter = painterResource(id = cardImage),
                contentDescription = "Maggi Noodles",
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.Center),
                contentScale = ContentScale.Fit
            )

            // Veg Icon
            Image(
                painter = painterResource(R.drawable.veg_icon),
                contentDescription = "Veg Icon",
                modifier = Modifier
                    .size(20.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .align(Alignment.BottomStart)
                    .padding(start = 5.dp, bottom = 5.dp)
            )

            // ADD Button
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = 5.dp, y = 5.dp)
            ) {

                Button(
                    onClick = { },
                    modifier = Modifier
                        .size(width = 58.dp, height = 36.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(
                            width = 1.dp,
                            color = colorResource(R.color.green),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .offset(y = (-2).dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = colorResource(R.color.green)
                    ),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "ADD",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "2 options",
                            fontSize = 10.sp,
                            color = Color.Black.copy(alpha = 0.5f)
                        )
                    }
                }
            }
        }

        // Product Details Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 9.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "280 g",
                    fontSize = 8.sp,
                    color = Color.Blue.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(
                            color = Color.LightGray.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(5.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )

                Text(
                    text = "Instant Noodles",
                    fontSize = 8.sp,
                    color = Color.Blue.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 3,
                lineHeight = 17.sp,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.offset(y = (-5).dp)
            ) {
                repeat(5) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFB800),
                        modifier = Modifier.size(12.dp)
                    )
                }

                Text(
                    text = "(88,764)",
                    fontSize = 9.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.offset(y = (-14).dp)
            ) {

                Icon(
                    painter = painterResource(R.drawable.timer),
                    contentDescription = null,
                    modifier = Modifier.size(11.dp)
                )

                Text(
                    text = "14 MINS",
                    fontSize = 8.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 4.dp)
                )

                Text(
                    text = "₹60",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.offset(y = (-2).dp)
                )
            }
        }
    }
}

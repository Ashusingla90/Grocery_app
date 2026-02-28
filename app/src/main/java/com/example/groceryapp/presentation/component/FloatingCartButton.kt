package com.example.groceryapp.presentation.component


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groceryapp.R

@Composable
fun FloatingCartButton(
    itemCount: Int,
    onClick: () -> Unit,
    onDismiss: () -> Unit
) {

    AnimatedVisibility(
        visible = itemCount > 0,
        enter = slideInVertically { it } + fadeIn(),
        exit = slideOutVertically { it } + fadeOut()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 2.dp),
            contentAlignment = Alignment.Center
        ) {

            Card(
                modifier = Modifier
                    .width(170.dp)
                    .height(70.dp)
                    .padding(bottom = 16.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .clickable { onClick() },
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.green)
                )
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.milk),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.White, CircleShape),
                        tint = Color.Unspecified
                    )

                    Spacer(modifier = Modifier.width(3.dp))

                    Column {

                        Text(
                            text = "View cart",
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "$itemCount ITEM",
                            fontSize = 15.sp,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.width(3.dp))

                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = colorResource(id = R.color.ViewActivityClickableColor),
                                shape = CircleShape
                            ),
                        tint = Color.Unspecified
                    )
                }
            }
        }
    }
}

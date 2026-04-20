package com.example.groceryapp.presentation.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import com.example.groceryapp.R
import com.example.groceryapp.data.models.BlinkItCategoryData

data class TabItem(
    val title: String,
    val icon: Int
)

@Composable
fun BlinkitProTabRow(
    onTabSelected: (Int) -> Unit) {

    val tabs = listOf(
        TabItem("All", R.drawable.ic_all),
        TabItem("Summer", R.drawable.ic_summer),
        TabItem("Electronics", R.drawable.ic_electronics),
        TabItem("Beauty", R.drawable.ic_beauty),
        TabItem("Kitchen", R.drawable.ic_kitchen)
    )

    var selectedIndex by remember { mutableStateOf(0) }

    ScrollableTabRow(
        selectedTabIndex = selectedIndex,
        containerColor = Color(0xFFFFC107),
        edgePadding = 12.dp,
        divider = {},
        indicator = { tabPositions ->

            Box(
                Modifier
                    .tabIndicatorOffset(tabPositions[selectedIndex])
                    .height(3.dp)
                    .background(Color.Black)
            )
        }
    ) {

        tabs.forEachIndexed { index, tab ->

            val isSelected = selectedIndex == index

            val animatedColor by animateColorAsState(
                targetValue = if (isSelected) Color.Black else Color.Black.copy(0.6f),
                label = ""
            )

            Tab(
                selected = isSelected,
                onClick = {
                    selectedIndex = index
                    onTabSelected(index)
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(alpha = 0.6f),
            ) {

                Column(
                    modifier = Modifier.padding(vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(tab.icon),
                        contentDescription = tab.title,
                        modifier = Modifier
                            .size(22.dp)
                            .scale(if (isSelected) 1.1f else 1f)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = tab.title,
                        fontSize = 13.sp,
                        fontWeight = if (isSelected)
                            FontWeight.Bold else FontWeight.Normal,
                        color = animatedColor
                    )
                }
            }
        }
    }
}
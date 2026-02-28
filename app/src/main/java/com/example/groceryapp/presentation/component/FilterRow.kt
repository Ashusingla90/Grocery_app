package com.example.groceryapp.presentation.component


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groceryapp.R

@Composable
fun FiltersRow(filters: List<String>) {

    var selectedFilter by remember { mutableStateOf<String?>(null) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        LazyRow(
            modifier = Modifier.weight(2f),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            items(filters) { filter ->

                FilterChip(
                    onClick = {
                        selectedFilter =
                            if (selectedFilter == filter) null else filter
                    },

                    selected = selectedFilter == filter,

                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = Color.White,
                        labelColor = Color.DarkGray
                    ),

                    leadingIcon = {

                        if (filter == "Filter") {
                            Icon(
                                painter = painterResource(id = R.drawable.milk),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        if (filter == "Tomato") {
                            Icon(
                                painter = painterResource(id = R.drawable.milk),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        if (filter == "Apple") {
                            Icon(
                                painter = painterResource(id = R.drawable.milk),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        if (filter == "Kiwi") {
                            Icon(
                                painter = painterResource(id = R.drawable.milk),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        if (filter == "Vegetables") {
                            Icon(
                                painter = painterResource(id = R.drawable.milk),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    },

                    trailingIcon = {

                        if (filter == "Filter") {
                            Icon(
                                painter = painterResource(R.drawable.arrowdown),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        if (selectedFilter == "Tomato" && filter == "Tomato") {
                            Icon(
                                painter = painterResource(R.drawable.close),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        if (selectedFilter == "Apple" && filter == "Apple") {
                            Icon(
                                painter = painterResource(R.drawable.close),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        if (selectedFilter == "Kiwi" && filter == "Kiwi") {
                            Icon(
                                painter = painterResource(R.drawable.close),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        if (selectedFilter == "Vegetables" && filter == "Vegetables") {
                            Icon(
                                painter = painterResource(R.drawable.close),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    },

                    label = {
                        Text(
                            text = filter,
                            fontSize = 12.sp
                        )
                    },

                    enabled = true,

                    border = BorderStroke(
                        width = 0.4.dp,
                        color = colorResource(R.color.gray)
                    )
                )
            }
        }
    }
}

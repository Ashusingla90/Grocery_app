package com.example.groceryapp.presentation.component


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groceryapp.R

@Composable
fun NoodlesDownList() {

    Text(
        text = "Noodles",
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
        fontSize = 18.sp,
        letterSpacing = 1.sp
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MaggiNoodleItem(
            R.drawable.milk,
            title = "Maggi Masala - 2 Minutes Instant Noodles"
        )

        MaggiNoodleItem(
            R.drawable.milk,
            title = "Maggi Masala - 2 Minutes Instant Noodles"
        )

        MaggiNoodleItem(
            R.drawable.milk,
            title = "Maggi Masala - 2 Minutes Instant Noodles"
        )
    }

    Spacer(modifier = Modifier.height(10.dp))

    Text(
        text = "Soft Drinks",
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
        fontSize = 18.sp,
        letterSpacing = 1.sp
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        MaggiNoodleItem(
            R.drawable.pepsi,
            title = "Pepsi Up Soft Drink (750 ml)"
        )

        MaggiNoodleItem(
            R.drawable.cocacola,
            title = "Coca-Cola Diet Coke Diets & Lights-Pack"
        )

        MaggiNoodleItem(
            R.drawable.sprite,
            title = "Sprite Lime Flavored Soft Drink 750 ml"
        )
    }
}

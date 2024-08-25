package com.example.hostcompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import com.example.hostcompose.ui.theme.HostComposeTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.animation.Crossfade

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource


@Composable
fun HomeContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        Text("This is the Home tab")
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun WondrLogo() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp,
                vertical = 6.dp
            )

    ) {
        val svgResource = "" // Assuming your file is named ic_wondr_logo.svg
        val icon = @Composable {
            Icon(
                painter = painterResource(id = R.drawable.icon_common_logout),
                contentDescription = "Add",
                tint = Color.Black

            ) // Replace with your icon
        }
        Image(
            painter = painterResource(id = R.drawable.ic_wondr_logo),
            contentDescription = null,
            modifier = Modifier
                .width(100.01283.dp)
                .height(35.67394.dp)
        )
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
            ),
            border = BorderStroke(width = 1.dp, color = Color(0xFFDADADA)),
            contentPadding = PaddingValues(horizontal = 12.dp),
            modifier = Modifier
                .height(32.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start, // Align to start
                verticalAlignment = Alignment.CenterVertically
            ) {
                icon()
                Spacer(modifier = Modifier.width(8.dp)) // Add spacing between icon and text
                Text(
                    text = "Keluar",

                    // Mobile/Label/Label 2 - DemiBold
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 14.4.sp,
                        fontWeight = FontWeight(900),
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }
    }
}


@Composable
fun BarMenu(){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp,
                vertical = 6.dp
            )
    ) {
        val iconNotification = @Composable {
            Icon(
                painter = painterResource(id = R.drawable.ic_common_notifications),
                contentDescription = "Add",
                tint = Color.Black

            ) // Replace with your icon
        }
        val iconHistroy = @Composable {
            Icon(
                painter = painterResource(id = R.drawable.ic_common_transaction_history),
                contentDescription = "Add",
                tint = Color.Black

            ) // Replace with your icon
        }
        val iconMenu = @Composable {
            Icon(
                painter = painterResource(id = R.drawable.ic_common_menu),
                contentDescription = "Add",
                tint = Color.Black

            ) // Replace with your icon
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_image), // Ganti dengan nama file gambar Anda
                contentDescription = "Description of the image",
                modifier = Modifier
                    .size(25.dp) // Ubah ukuran sesuai kebutuhan Anda
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Hai, Putri Negara!")
        }

        Row ( ){
            iconNotification()
            Spacer(modifier = Modifier.width(16.dp))
            iconHistroy()
            Spacer(modifier = Modifier.width(16.dp))
            iconMenu()
        }

    }
}
package com.example.a2d_golf.userinterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.a2d_golf.R

@Composable
fun StartPage(modifier: Modifier) {
    val offset = Offset(5.0f, 10.0f)

    Box(
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    //TODO GAMEVIEW 
                }
            }
    ) {
        Column(
            modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "2D Golf",
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 80.sp,
                    shadow = Shadow(
                        color = Color.Gray, offset = offset, blurRadius = 3f
                    )
                ),
            )
        }
        Image(
            painter = painterResource(id = R.drawable.start_background),
            contentDescription = null,
            modifier.fillMaxSize()
        )
    }
}
package com.example.a2d_golf

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.AssistChipDefaults.scale

@Composable
fun Ball(
    ballState : BallState,
    posX : Float,
    posY : Float,


) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        translate(left = posX, top = posY) {
            drawCircle(Color.Black, radius = 10.dp.toPx())
        }
    }
}
package com.example.a2d_golf.userinterface

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.example.a2d_golf.BallState

@Composable
fun BallTracer(modifier : Modifier, ballState: BallState) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        for (b in ballState.prevPositions) {
            drawCircle(
                center = Offset(x = b.xPos, y = b.yPos),
                radius = ballState.RADIUS - 5f,
                color = Color.Green
            )
        }
    }
}
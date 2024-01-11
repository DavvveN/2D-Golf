package com.example.a2d_golf.userinterface

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.example.a2d_golf.BallState

@Composable
fun Ball( modifier: Modifier,ballState: BallState) {
    Box(modifier = modifier.fillMaxSize()){
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                center = Offset(x = ballState.position.xPos, y = ballState.position.yPos),
                radius = ballState.RADIUS,
                color = Color.Yellow
            )
        }
    }
}
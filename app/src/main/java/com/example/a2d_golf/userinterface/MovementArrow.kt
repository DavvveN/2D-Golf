package com.example.a2d_golf.userinterface

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.example.a2d_golf.MovementArrowState
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun MovementArrow(modifier : Modifier,arrowState : MovementArrowState ){
    //TODO function to draw movement arrow
    val eX = arrowState.position.xPos + arrowState.magnitude * cos(arrowState.orientation.toDouble())
    val eY = arrowState.position.yPos - arrowState.magnitude * sin(arrowState.orientation.toDouble())

    Box(modifier = Modifier.fillMaxSize()){
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawLine(
                start = Offset(x = arrowState.position.xPos, y = arrowState.position.yPos),
                end = Offset(x = eX.toFloat(), y = eY.toFloat()),
                color = Color(255, 255, 255),
                strokeWidth = 30f
            )
        }
    }
}
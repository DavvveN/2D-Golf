package com.example.a2d_golf.userinterface

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import com.example.a2d_golf.MovementArrowState
import com.example.a2d_golf.Vector2
import android.graphics.Paint
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun MovementArrow(modifier : Modifier,arrowState : MovementArrowState ){
    //TODO function to draw movement arrow
    val paint = Paint()
    paint.color = Black.value.toInt()
    paint.strokeWidth = 10f
    paint.style = Paint.Style.STROKE
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures{ change, dragAmount ->
                    change.consume()
                    arrowState.interaction(arrowState.position.sub(Vector2(dragAmount.x,dragAmount.y)))
                }
            }
        ){
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawLine(
                start = Offset(x = arrowState.bState.value.position.xPos, y = arrowState.bState.value.position.yPos),
                end = Offset(x = arrowState.position.xPos, y = arrowState.position.yPos),
                color = Color(255, 255, 255),
                strokeWidth = 30f
            )
        }
    }

}
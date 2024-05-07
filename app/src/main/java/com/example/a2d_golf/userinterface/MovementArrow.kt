package com.example.a2d_golf.userinterface

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import com.example.a2d_golf.MovementArrowState
import com.example.a2d_golf.Vector2
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MovementArrow(modifier: Modifier, arrowState: StateFlow<MovementArrowState>){

    var aS = arrowState.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        change.consume()
                        aS.value.interaction(
                            aS.value.position.sub(
                                Vector2(
                                    dragAmount.x,
                                    dragAmount.y
                                )
                            )
                        )
                    },
                    onDragEnd = {
                        aS.value.launchBall()
                    }
                )
            }
        ){
        if(aS.value.display){
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawLine(
                    start = Offset(x = aS.value._bState.value.position.xPos, y = aS.value._bState.value.position.yPos),
                    end = Offset(x = aS.value.position.xPos, y = aS.value.position.yPos),
                    color = Color(255, 255, 255),
                    strokeWidth = 30f
                )
            }
        }
    }

}
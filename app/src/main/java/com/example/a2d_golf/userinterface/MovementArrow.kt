package com.example.a2d_golf.userinterface

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import com.example.a2d_golf.MovementArrowState
import com.example.a2d_golf.Vector2
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.drawscope.draw
import com.example.a2d_golf.BallState
import com.example.a2d_golf.consts.MiscConst
import com.example.a2d_golf.consts.PhysicsConst
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun MovementArrow(modifier : Modifier,arrowState : MovementArrowState ){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        change.consume()
                        arrowState.interaction(
                            arrowState.position.sub(
                                Vector2(
                                    dragAmount.x,
                                    dragAmount.y
                                )
                            )
                        )
                    },
                    onDragEnd = {
                        arrowState.launchBall()
                    }
                )
            }
        ){
        if(arrowState.display){
            val bState = arrowState._bState.collectAsState()
//
            val path = Path()
            path.moveTo(bState.value.position.xPos, bState.value.position.yPos)
            val x1 = bState.value.position.xPos + (arrowState.position.xPos - bState.value.position.xPos) / 2
            val y1 = arrowState.position.yPos
            path.quadraticBezierTo(x1,y1,arrowState.position.xPos, arrowState.position.yPos)

            // Create a rectangle that covers the area underneath the curve
            val rect = Rect(
                left = min(bState.value.position.xPos, arrowState.position.xPos),
                top = min(bState.value.position.yPos, arrowState.position.yPos),
                right = max(bState.value.position.xPos, arrowState.position.xPos),
                bottom = max(bState.value.position.yPos, arrowState.position.yPos)
            )

            val path2 = Path().apply {
                addRect(rect)
            }


            // Subtract the area underneath the curve from the path
            val nP = Path()
            nP.op(path, path2 ,PathOperation.Difference)


            Canvas(modifier = Modifier.fillMaxSize()) {

                drawPath(
                    path = path,
                    color = Color.Cyan,
                    style = Stroke(width = 30f)

                )

            }
        }
    }

}
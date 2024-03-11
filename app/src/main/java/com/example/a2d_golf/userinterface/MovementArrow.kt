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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.a2d_golf.consts.MiscConst
import com.example.a2d_golf.consts.PhysicsConst
import kotlinx.coroutines.delay
import kotlin.math.abs
import kotlin.math.round

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
            val miscConst: MiscConst = MiscConst()
            val amountDisplayBalls = round(abs(arrowState.position.xPos - bState.value.position.xPos) / miscConst.DISTANCE_BETWEEN_BALLS)
            val prevPositions: ArrayList<Vector2> = arrayListOf()
            val physicsConst: PhysicsConst = PhysicsConst()

            var userForce = arrowState.position.sub(bState.value.position)

            //temp bState (not a state)
            val newBState = bState.value.copy()

            //Simulates the ball trajectory for the 6 next ball position
            while(prevPositions.size < amountDisplayBalls){
                val deltaTime = 1f

                val gravityForce = Vector2.VectorConst.UP.scale(physicsConst.GRAVITY)
                val newVelocity = newBState.velocity.add(gravityForce.scale(deltaTime))

                val uF = userForce.scale(physicsConst.USERFACTOR).scale(deltaTime)
                newVelocity.add(uF)

                userForce = Vector2.VectorConst.EMPTY

                val newPosition = newBState.position.add(newVelocity.scale(deltaTime))

                if(newBState.prevPos.xPos + miscConst.DISTANCE_BETWEEN_BALLS < newPosition.xPos ){
                    prevPositions.add(newPosition)
                    newBState.prevPos = newPosition
                }


            }

            Canvas(modifier = Modifier.fillMaxSize()) {
                drawLine(
                    start = Offset(x = arrowState._bState.value.position.xPos, y = arrowState._bState.value.position.yPos),
                    end = Offset(x = arrowState.position.xPos, y = arrowState.position.yPos),
                    color = Color(255, 255, 255),
                    strokeWidth = 30f
                )
            }
        }
    }

}
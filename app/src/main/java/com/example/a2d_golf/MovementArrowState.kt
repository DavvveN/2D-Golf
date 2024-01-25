package com.example.a2d_golf

import android.util.Log
import com.example.a2d_golf.consts.PhysicsConst
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.acos
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.tan
import androidx.compose.ui.graphics.Path

data class MovementArrowState(
    var position: Vector2,
    var display: Boolean,
    var ascending : Boolean,
    var changeOrientation : Boolean,
    val bState : StateFlow<BallState>,
    val physicsConst: PhysicsConst = PhysicsConst(),
    var path: Path
){
    private lateinit var offsetVector2 : Vector2
    fun interaction(offsetVector2 : Vector2){
        offsetVector2.also { this.offsetVector2 = it }
        this.position.xPos = bState.value.position.xPos * 2 - offsetVector2.xPos
        this.position.yPos = bState.value.position.yPos * 2 - offsetVector2.yPos

        // An attempt to make a curved line that illustrates where you want to "shoot" the ball
        
//        val alpha = atan(position.yPos / position.xPos)
//
//        var points = ArrayList<Vector2>(1000)
//        val listSize = 1000
//        val v0 = 5f
//
//        val dx = (position.xPos - bState.value.position.xPos)/listSize
//
//        for(i in 0 until listSize){
//            val x = dx * i
//            val y = x * tan(alpha) - ((1 * x * x) / (2 * v0 * v0 * cos(alpha) * cos(alpha)))
//            points.add(Vector2(x,y))
//        }
//
//        path = Path().apply {
//            moveTo(bState.value.position.xPos + points[0].xPos, bState.value.position.yPos + points[0].yPos)
//            for (i in 1 until listSize) {
//                lineTo(bState.value.position.xPos + points[i].xPos, bState.value.position.yPos + points[i].yPos)
//            }
//        }

    }

    fun launchBall(){

    }
}
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
    var path: Path,
    var offsetVector2: Vector2 = Vector2(0f,0f)
){
    fun interaction(position : Vector2){
        position.also{this.position = it}
        launchBall()
    }

    fun launchBall(){
        
    }
}
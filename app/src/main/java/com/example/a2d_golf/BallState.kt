package com.example.a2d_golf

import android.graphics.RectF
data class BallState(
    var position : Vector2,
    var velocity : Vector2,
){
    val RADIUS : Float = 10f

}
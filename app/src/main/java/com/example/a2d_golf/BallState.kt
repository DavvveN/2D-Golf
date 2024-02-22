package com.example.a2d_golf

import android.graphics.Point

data class BallState(
    var position: Vector2,
    var velocity: Vector2,
    var userForce: Vector2,
    var prevPos: Vector2 = position,
    val prevPositions: ArrayList<Vector2> = arrayListOf()
) {
    val RADIUS: Float = 20f

}
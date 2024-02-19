package com.example.a2d_golf

data class BallState(
    var position : Vector2,
    var velocity : Vector2,
    var userForce : Vector2,
    var prevPos: Vector2 = position
){
    val RADIUS : Float = 20f

}
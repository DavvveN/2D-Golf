package com.example.a2d_golf

data class MovementArrowState(
    var position: Vector2,
    var orientation: Double,
    var display: Boolean,
    val magnitude: Float = 300f,
    var ascending : Boolean,
    var changeOrientation : Boolean
){
}
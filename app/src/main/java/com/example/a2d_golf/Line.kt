package com.example.a2d_golf

class Line( val p1 : Point, val p2 : Point) : Drawable() {
    private val dx = p2.x- p1.x
    private val dy = p1.y - p1.y

    val k = if (dx != 0f) dy/dx else 0f
    val m = p1.y - (k * p1.x)

    var normal = Vector2(-dy,dx).normalize()
}
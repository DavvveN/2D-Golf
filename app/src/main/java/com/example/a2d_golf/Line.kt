package com.example.a2d_golf

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Line( val p1 : Point, val p2 : Point) : Drawable {
    private val dx = p2.x- p1.x
    private val dy = p2.y - p1.y
    private val physicsConst = PhysicsConst()

    private val k = if (dx != 0f) dy/dx else 0f
    private val m = p1.y - (k * p1.x)

    private var normal = Vector2(dy,-dx).normalize()
    override fun handleCollision(_bState: MutableStateFlow<BallState>) : MutableStateFlow<BallState> {
        val newY = intersectPointY(_bState.value.position.xPos)
        val newV = normal.reflection(_bState.value.velocity.scale(physicsConst.BOUNCE_FACTOR))
        return MutableStateFlow(BallState(Vector2(_bState.value.position.xPos,newY),newV))
    }

    override fun collidesWith(bState: StateFlow<BallState>): Boolean {
        if(bState.value.position.xPos >= p1.x && bState.value.position.xPos <= p2.x){
            Log.i("abcdefg", dy.toString())
            return intersectPointY(bState.value.position.xPos) <= bState.value.position.yPos
        }
        return false
    }

    fun intersectPointY(xPos : Float) : Float{
        return k * xPos + m
    }
}
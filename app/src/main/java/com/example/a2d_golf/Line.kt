package com.example.a2d_golf

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Line( val p1 : Point, val p2 : Point) : Drawable {
    private val dx = p2.x- p1.x
    private val dy = p1.y - p1.y
    private val physicsConst = PhysicsConst()

    val k = if (dx != 0f) dy/dx else 0f
    val m = p1.y - (k * p1.x)

    var normal = Vector2(-dy,dx).normalize()
    override fun handleCollision(_bState: MutableStateFlow<BallState>) : MutableStateFlow<BallState> {
        _bState.value.position.yPos = intersectPointY(_bState.value.position.xPos)
        //_bState.value.velocity = normal.reflection(_bState.value.velocity.scale(physicsConst.BOUNCE_FACTOR))
        _bState.value.velocity = _bState.value.velocity.scale(-1f)
        return MutableStateFlow(BallState(_bState.value.position, _bState.value.velocity))
    }

    override fun collidesWith(bState: StateFlow<BallState>): Boolean {
        if(bState.value.position.xPos >= p1.x && bState.value.position.xPos <= p2.x){
            return intersectPointY(bState.value.position.xPos) <= bState.value.position.yPos
        }
        return false
    }

    fun intersectPointY(xPos : Float) : Float{
        return k * xPos + m
    }
}
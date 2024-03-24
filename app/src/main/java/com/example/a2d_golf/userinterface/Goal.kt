package com.example.a2d_golf.userinterface

import com.example.a2d_golf.BallState
import com.example.a2d_golf.Vector2
import kotlinx.coroutines.flow.StateFlow

class Goal(val p1 : Point, val p2 : Point) : Drawable{

    override fun collidesWith(bState: StateFlow<BallState>): Boolean {
        if(bState.value.position.xPos >= p1.x && bState.value.position.xPos <= p2.x){
            if(bState.value.position.yPos>= p1.y){
                return true
            }
        }
        return false
    }

    override fun getStartPointAsVector(): Vector2 {
        return Vector2(p1.x, p1.y)
    }
}
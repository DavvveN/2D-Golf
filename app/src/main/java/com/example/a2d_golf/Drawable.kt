package com.example.a2d_golf

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface Drawable {
    fun handleCollision(_bState: MutableStateFlow<BallState>): MutableStateFlow<BallState>
    fun collidesWith(bState: StateFlow<BallState>) : Boolean


}
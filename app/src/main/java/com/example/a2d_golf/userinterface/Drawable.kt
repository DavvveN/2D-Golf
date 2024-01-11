package com.example.a2d_golf.userinterface

import com.example.a2d_golf.BallState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface Drawable {
    fun handleCollision(_bState: MutableStateFlow<BallState>): MutableStateFlow<BallState>
    fun collidesWith(bState: StateFlow<BallState>) : Boolean


}
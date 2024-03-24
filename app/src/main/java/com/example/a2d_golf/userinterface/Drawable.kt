package com.example.a2d_golf.userinterface

import com.example.a2d_golf.BallState
import com.example.a2d_golf.Vector2
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface Drawable {
    fun collidesWith(bState: StateFlow<BallState>) : Boolean
    fun getStartPointAsVector() : Vector2


}
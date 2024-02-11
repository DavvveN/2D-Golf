package com.example.a2d_golf

import android.app.Application
import android.util.Log
import androidx.compose.ui.graphics.Path
import androidx.lifecycle.AndroidViewModel
import com.example.a2d_golf.consts.PhysicsConst
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class GameStatus{
    FALLING,
    ON_GROUND,
    IN_MOTION
}

data class GameState(
    var status :GameStatus = GameStatus.FALLING,
    val ballState: BallState = BallState(velocity = Vector2(0f,0f),position = Vector2(400f,600f), userForce = Vector2(0f,0f)),
    val levelData: LevelData
)
class GameViewModel(application: Application) : AndroidViewModel(application) {
    //                        LEARNING KOTLIN JETPACK:
    // ***********************************************************************
    // _gameState and _bState are mutableStateFlows and thus are changeable
    // gameState and bState derive from _gameState and _bState and are read only
    // We can thus control changeability or readability in a given function
    // ***********************************************************************a


    private var _gameState = MutableStateFlow(GameState(levelData = LevelData()))
    var gameState = this._gameState.asStateFlow()
    private var _bState = MutableStateFlow(this.gameState.value.ballState)
    var bState = _bState.asStateFlow()

    private var _movementArrowState = MutableStateFlow(MovementArrowState(position = bState.value.position, display = true, _bState = this._bState, physicsConst = PhysicsConst()))
    var movementArrowState = _movementArrowState.asStateFlow()

    private val physicsConst = PhysicsConst()
    fun update(deltaTime : Float){

        handleFalling(deltaTime)
        //orientMovementArrow(deltaTime,_movementArrowState)
    }

    private fun handleFalling(deltaTime : Float){

        val gravityForce = Vector2.VectorConst.UP.scale(physicsConst.GRAVITY)
        val newVelocity = bState.value.velocity.add(gravityForce.scale(deltaTime))

        //TODO CHECK DIRECTION OF USER VECTOR
        val uF = bState.value.userForce.scale(physicsConst.USERFACTOR).scale(deltaTime)
        if(uF.xPos != 0f ||uF.yPos != 0f){
        Log.i("abcdefx",uF.yPos.toString())
        Log.i("abcdefy",uF.yPos.toString())
        }


        newVelocity.add(uF)
        _bState.value.userForce = Vector2.VectorConst.EMPTY

        // Change position in relation to velocity * time
        val newPosition = bState.value.position.add(newVelocity.scale(deltaTime))
        _bState.value = BallState(velocity = newVelocity.copy(), position = newPosition.copy(), userForce = bState.value.userForce)
        ResolveCollision()
    }

    private fun ResolveCollision(){
        for(d in gameState.value.levelData.firstLevel){
            //Check which line share the same x value ie. which line is under the ball
            if(d.collidesWith(bState)){
                _bState.value = d.handleCollision(_bState).value
            }
        }
    }

}
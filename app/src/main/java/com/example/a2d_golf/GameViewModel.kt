package com.example.a2d_golf

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.a2d_golf.consts.MiscConst
import com.example.a2d_golf.consts.PhysicsConst
import com.example.a2d_golf.userinterface.Goal
import com.example.a2d_golf.userinterface.Line
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.abs

enum class GameStatus {
    START,
    GAME,
    SETTINGS,
    LEVELS
}

data class GameState(
    var status: GameStatus = GameStatus.START,
    val levelData: LevelData,
    var currentLevel: Int = 1,
    var victory : Boolean = false,
    var promptRestart : Boolean = false,
    var userFactor : Float = PhysicsConst().USERFACTOR,
    var start : Boolean = true
)

data class SettingsState(
    var volume: Float = 0.5f
)

class GameViewModel(application: Application) : AndroidViewModel(application) {
    var _gameState = MutableStateFlow(GameState(levelData = LevelData()))
    var gameState = this._gameState.asStateFlow()
    private var _bState = MutableStateFlow(
        BallState(
            velocity = Vector2(0f, 0f),
            position = gameState.value.levelData.levels.get(gameState.value.currentLevel).get(0).getStartPointAsVector(),
            userForce = Vector2(0f, 0f)
        )
    )
    var bState = _bState.asStateFlow()
    var _settingState = MutableStateFlow(SettingsState())
    var settingsState = _settingState.asStateFlow()

    private val physicsConst = PhysicsConst()

    var _movementArrowState = MutableStateFlow(
        MovementArrowState(
            position = bState.value.position,
            display = true,
            _bState = this._bState,
            physicsConst = PhysicsConst()
        )
    )
    var movementArrowState = _movementArrowState.asStateFlow()

    fun update(deltaTime: Float) {
        handleMovement(deltaTime)
    }

    fun startMusic(){
        _gameState.value = gameState.value.copy(start = false)
    }

    fun restart() {
        _bState.value = bState.value.copy(
            velocity = Vector2(0f, 0f),
            position = gameState.value.levelData.levels.get(gameState.value.currentLevel).get(0).getStartPointAsVector(),
            userForce = Vector2.VectorConst.EMPTY,
            prevPositions = arrayListOf()
        )

        _movementArrowState.value = movementArrowState.value.copy(
            display = true,
            position = gameState.value.levelData.levels.get(gameState.value.currentLevel).get(0).getStartPointAsVector(),
            _bState = _bState
        )

        startGame()
    }

    fun startGame() {
        _gameState.value = gameState.value.copy(status = GameStatus.GAME, promptRestart = false)
    }

    fun levelView(victory : Boolean) {
        _gameState.value = gameState.value.copy(status = GameStatus.LEVELS, victory = victory)
    }

    fun showSettings() {
        _gameState.value = gameState.value.copy(status = GameStatus.SETTINGS)
    }
    fun promptRestart(){
        _gameState.value = gameState.value.copy(promptRestart = true)
    }


    //Handles more than falling: also applies a user force
    private fun handleMovement(deltaTime: Float) {

        if((bState.value.velocity.xPos != 0f && abs(bState.value.velocity.xPos) < 0.000001f)||bState.value.position.xPos < 0f || bState.value.position.xPos > 2500f){
            promptRestart()
        }

        val gravityForce = Vector2.VectorConst.UP.scale(physicsConst.GRAVITY)
        val newVelocity = bState.value.velocity.add(gravityForce.scale(deltaTime))

        val uF = bState.value.userForce.scale(gameState.value.userFactor).scale(deltaTime)
        newVelocity.add(uF)

        _bState.value.userForce = Vector2.VectorConst.EMPTY

        // Change position in relation to velocity * time
        val newPosition = bState.value.position.add(newVelocity.scale(deltaTime))
        var prevPos = bState.value.prevPos
        val pP: ArrayList<Vector2> = bState.value.prevPositions

        //IF THE BALL IS AT THE NEXT INTERVAL TO ADD ANOTHER DOT AND IS GOING IN THAT DIRECTION
        if ((bState.value.prevPos.xPos + MiscConst().DISTANCE_BETWEEN_BALLS < newPosition.xPos && bState.value.velocity.xPos > 0)
            || (bState.value.prevPos.xPos - MiscConst().DISTANCE_BETWEEN_BALLS > newPosition.xPos && bState.value.velocity.xPos < 0)
        ) {
            pP.add(newPosition)
            prevPos = newPosition
        }

        _bState.value = BallState(
            velocity = newVelocity.copy(),
            position = newPosition.copy(),
            userForce = bState.value.userForce,
            prevPos = prevPos,
            prevPositions = pP
        )
        ResolveCollision()
    }

    //Detects and resolves collisions
    private fun ResolveCollision() {
        val level = gameState.value.levelData.levels.get(gameState.value.currentLevel)
        for (d in level) {
            //Check which line share the same x value ie. which line is under the ball
            if (d.collidesWith(bState)) {
                if (d is Line) {
                    _bState.value = d.handleCollision(_bState).value
                } else if (d is Goal) {
                    levelView(victory = true)
                }

                //TODO SOUND EFFECT IF DISPLAY = OFF
            }
        }
    }

}
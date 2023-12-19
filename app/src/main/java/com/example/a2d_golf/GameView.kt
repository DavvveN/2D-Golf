package com.example.a2d_golf

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border


@Composable
fun GameView(modifier: Modifier = Modifier){
    Background();
    DisplayLevelImage();
    DisplayFlag();
}

@Composable
fun Background(modifier : Modifier = Modifier){
    Column {
        Box {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
                modifier = modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun DisplayLevelImage(modifier: Modifier = Modifier){
    Column{
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        ){
            Image(
                painter = painterResource(id = R.drawable.firstmap),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .border(BorderStroke(2.dp, SolidColor(Color.Red)))
            )
        }
    }
}

@Composable
fun DisplayFlag(modifier: Modifier = Modifier){
    Column{
        Box(){
            Image(
                painter = painterResource(id = R.drawable.flagga),
                contentDescription = null,
                modifier = modifier
                    .fillMaxSize(0.3f)
            )
        }
    }
}

package com.example.a2d_golf

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.a2d_golf.ui.theme._2DGolfTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _2DGolfTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    initGameView();
                }
            }
        }
    }
}

@Composable
fun initGameView(modifier: Modifier = Modifier) {
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
                    .fillMaxHeight())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun initGamePreview() {
    _2DGolfTheme {
        initGameView();
    }
}
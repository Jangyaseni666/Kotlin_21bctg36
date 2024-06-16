package com.example.androidsi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidsi.ui.theme.AndroidSITheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        val gColors = listOf(Color.Cyan, Color.Green, Color.Red)
        setContent {
            Column(modifier = Modifier.fillMaxSize()){
                Text(
                    text = "Implementing fontWeight, fontSize and fontColor",
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
                Text(
                    text = "Implementing fontFamily,text decoration and letter spacing",
                    fontFamily = FontFamily.Cursive,
                    fontSize = 30.sp,
                    textDecoration = TextDecoration.Underline,
                    letterSpacing = 2.sp
                )
                Text(
                    text="Implementing text alignment and testing whether it works or not",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
                Text(
                    text="Implementing line height and to do this we need two lines that can show difference",
                    lineHeight = 30.sp
                )
                Text(
                    text="Now to test overflow and softwrap we need to make sure the line goes out of the",
                    overflow = TextOverflow.Clip,
                    softWrap = false
                )
                Text(
                    text="For minlines and maxLines we need a paragraph of 2 lines: maxLines - An optional maximum number of lines for the text to span, wrapping if necessary\nminLines - The minimum height in terms of minimum number of visible lines",
                    minLines = 2,
                    maxLines = 3
                )
                Text(
                    text="implementing modifier",
                    modifier = Modifier.background(color = Color.Yellow),
                    color = Color.Red
                )
            }

        }

    }
}
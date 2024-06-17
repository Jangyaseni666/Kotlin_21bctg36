package com.example.androidsi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.androidsi.ui.theme.AndroidSITheme
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        val gColors = listOf(Color.Cyan, Color.Green, Color.Red)
        setContent {
            //LayOut Implemented using Column, Row and Box
            Column(modifier = Modifier.padding(5.dp)) {
                Row(
                    modifier = Modifier
                        .padding(5.dp)
                        .border(2.dp, Color.Black, RectangleShape)
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .align(Alignment.CenterVertically)
                            .height(40.dp)
                    ) {
                        Text(text = "Image", fontSize = 20.sp)
                        Text(text = "play", fontSize = 20.sp)
                    }
                    Column() {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "text1", fontSize = 30.sp)
                            Text(text = "text2", fontSize = 30.sp)
                            Text(text = "text3", fontSize = 30.sp)
                        }
                        Row {
                            Text(text = "icon1", modifier = Modifier
                                .clickable { println("Song playing") }, fontSize = 20.sp
                            )
                            Text(text = "icon2", fontSize = 20.sp)
                            Text(text = "icon3", fontSize = 20.sp)
                        }
                    }
                }
                // Alert Dialog implemented for all 4 buttons
                TonalButton()
                FilledButton()
                OutlinedButtonFunction()
                TextButtonFunction()
            }
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TonalButton(){
    var showAlert = remember {
        mutableStateOf(false)
    }
    val type:String ="Filled Tonal"
    Column{

        FilledTonalButton(onClick = { showAlert.value= !showAlert.value}) {
            Text(text="hello")
        }
        if(showAlert.value) AlertDialogFunction (
            type,
            onConfirmation = {showAlert.value = false}
        )
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FilledButton(){
    var showAlert = remember {
        mutableStateOf(false)
    }
    val type:String ="Filled"
    Column{
        Button(onClick = { showAlert.value= !showAlert.value}) {
            Text(text="hello")
        }
        if(showAlert.value) AlertDialogFunction (
            type,
            onConfirmation = {showAlert.value = false}
        )
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OutlinedButtonFunction(){
    var showAlert = remember {
        mutableStateOf(false)
    }
    val type:String ="Outlined"
    Column{
        OutlinedButton(onClick = { showAlert.value= !showAlert.value}) {
            Text(text="hello")
        }
        if(showAlert.value) AlertDialogFunction (
            type,
            onConfirmation = {showAlert.value = false}
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextButtonFunction(){
    var showAlert = remember {
        mutableStateOf(false)
    }
    val type:String ="Text"
    Column{
        TextButton(onClick = { showAlert.value= !showAlert.value}) {
            Text(text="hello")
        }
        if(showAlert.value) AlertDialogFunction (
            type,
            onConfirmation = {showAlert.value = false}
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlertDialogFunction(
    type:String,
    onConfirmation:() -> Unit
){
    AlertDialog(title = {Text(text="Alert from a $type button")}, onDismissRequest = {  }, confirmButton = {
        Button(onClick = onConfirmation) {
            Text(text = "OK")
        }
    }, text = {Text(text="Acknowledge me")})
}
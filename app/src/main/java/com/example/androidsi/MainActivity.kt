package com.example.androidsi

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import kotlin.math.round

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        val gColors = listOf(Color.Cyan, Color.Green, Color.Red)
        setContent {
            ScaffoldSample()
        }

    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ScaffoldSample() {
        // Implementing Scaffold
        Scaffold(
            topBar = {
                TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ), title = { Text(text = "Top bar is here", fontSize = 30.sp) })
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary
                ) {
                    Text(text = "Bottom bar is hereee")
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { println("Edit is clicked") }) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit button")
                }
            }
        ) {
            Column(modifier = Modifier.padding(it)) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF50f2e2)
                    ), modifier = Modifier
                        .size(width = 300.dp, height = 300.dp)
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Name Card",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(10.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Name : Jangyaseni Sahoo",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(10.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Desgination: Student",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(10.dp),
                        textAlign = TextAlign.Center
                    )
                }
                // Implement assist chip and also the close button
                var showChip by remember {
                    mutableStateOf(true)
                }
                if (showChip) {
                    AssistChip(
                        onClick = { showChip = false },
                        label = { Text(text = "assist chip") },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = "clear"
                            )
                        })
                }
                //Implement bottom sheetn using a button also add the chip back if it is removed
                var showBottom by remember {
                    mutableStateOf(false)
                }
                Button(
                    onClick = {
                        showBottom = true
                        showChip = true
                    }
                ) {
                    Text(text = "Click me")
                }
                if (showBottom) {
                    ModalBottomSheet(
                        onDismissRequest = { showBottom = false },
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "this is the bottom sheet",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                }
                // Implement slider within the range 0 and 10
                var sliderPos by remember {
                    mutableStateOf(0f)
                }
                Slider(
                    modifier = Modifier.padding(10.dp),
                    value = sliderPos,
                    onValueChange = { sliderPos = it },
                    steps=10,
                    valueRange = 0f..10f
                )
                Text(text = "The slider value is: ${round(sliderPos)}")
                // Implement Switch
                var checked by remember {
                    mutableStateOf(true)
                }
                Switch(checked = checked, onCheckedChange = {checked=it})
                //Red divider
                Divider(thickness = 3.dp, color = Color.Red)
                //Implemented the progress indicator
                if(checked)
                CircularProgressIndicator(modifier = Modifier.padding(5.dp))
                else {
                    Text(text = "The progress is stopped")
                }
            }
        }
    }
}



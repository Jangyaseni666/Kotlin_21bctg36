package com.example.androidsi

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.style.AlignmentSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.androidsi.ui.theme.AndroidSITheme
import kotlin.math.round

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        val gColors = listOf(Color.Cyan, Color.Green, Color.Red)
        setContent {
            //ScaffoldSample()
            //TextFieldSample()
            AppNavigation()
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
                    steps = 10,
                    valueRange = 0f..10f
                )
                Text(text = "The slider value is: ${round(sliderPos)}")
                // Implement Switch
                var checked by remember {
                    mutableStateOf(true)
                }
                Switch(checked = checked, onCheckedChange = { checked = it })
                //Red divider
                Divider(thickness = 3.dp, color = Color.Red)
                //Implemented the progress indicator
                if (checked)
                    CircularProgressIndicator(modifier = Modifier.padding(5.dp))
                else {
                    Text(text = "The progress is stopped")
                }
            }
        }
    }

    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "screen1") {
            composable("screen1") { Screen1(navController) }
            composable("screen2") { Screen2(navController) }
            composable("screen3/{name}", arguments = listOf(navArgument("name") {
                type = NavType.StringType
            })) { backStackEntry ->
                Screen3(navController, backStackEntry.arguments?.getString("name") ?: "")
            }
            composable(route = "screen4/{data}", arguments = listOf(navArgument("data") {
                type = NavType.StringType
            })) { backStackEntry ->
                Screen4(
                    navController = navController,
                    backStackEntry.arguments?.getString("data") ?: ""
                )
            }
            composable(route = "listsandgrids") { ListsAndGrids(navController) }
        }
    }

    @Composable
    fun Screen1(navController: NavController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(text = "Welcome to the Student Registration.", fontSize = 40.sp)
            Button(onClick = { navController.navigate("screen2") }) {
                Text(text = "Proceed")
            }
        }
    }

    @Composable
    fun Screen2(navController: NavController) {
        var name by remember {
            mutableStateOf("")
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(text = "Fill the following details:", color = Color.Red, fontSize = 40.sp)
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Enter your name") })
            Button(onClick = { navController.navigate("screen3/$name") }) {
                Text(text = "Next")
            }
        }
    }

    @Composable
    fun Screen3(navController: NavController, name: String) {
        var sic by remember {
            mutableStateOf("")
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(text = "Welcome $name.", color = Color.Cyan, fontSize = 40.sp)
            OutlinedTextField(value = sic, onValueChange = {
                sic = it
            }, label = { Text(text = "Enter your SIC") })
            Button(onClick = { navController.navigate("screen4/$sic") }) {
                Text(text = "Goto Screen 4")
            }
        }
    }

    @Composable
    fun Screen4(navController: NavController, data: String) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (data.startsWith("21")) {
                Text(
                    text = "Your registration is complete! Welcome to 4th year!!",
                    color = Color.Green,
                    fontSize = 40.sp
                )
            } else if (data.startsWith("22")) {
                Text(
                    text = "Your registration is complete! Welcome to 3rd year!!",
                    color = Color.Green,
                    fontSize = 40.sp
                )
            } else if (data.startsWith("23")) {
                Text(
                    text = "Your registration is complete! Welcome to 2nd year!!",
                    color = Color.Green,
                    fontSize = 40.sp
                )
            } else if (data.startsWith("24")) {
                Text(
                    text = "Your registration is complete! Welcome to 1st year!!",
                    color = Color.Green,
                    fontSize = 40.sp
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Click the below button to see lists and grid implementation",
                textAlign = TextAlign.Center
            )
            Button(onClick = { navController.navigate("listsandgrids") }) {
                Text(text = "click me")
            }
        }
    }

    @Composable
    fun ListsAndGrids(navController: NavController) {
        var checked by remember {
            mutableStateOf(true)
        }

        Column(modifier = Modifier.padding(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically){
                Text(text = "Grid", modifier = Modifier.padding(5.dp))
                Switch(checked = checked, onCheckedChange = { checked = it })
                Text(text = "Lists", modifier = Modifier.padding(5.dp))
            }
            if (checked) {
                LazyColumn {
                    items(10) {
                        Card(modifier = Modifier.size(width = 100.dp, height = 100.dp)) {
                            Text(text = "Card1")
                            Text(text = "Filler Text")
                        }
                    }
                }
            } else {
                LazyVerticalGrid(columns = GridCells.Adaptive(128.dp)) {
                    items(15) {
                        Card(modifier = Modifier.size(width = 100.dp, height = 100.dp)) {
                            Text(text = "Card1")
                            Text(text = "Filler Text")
                        }
                    }
                }
            }
        }

    }
}



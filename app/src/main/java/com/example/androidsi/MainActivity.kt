package com.example.androidsi

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.style.AlignmentSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
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
            ComposeLogin()
            //CounterView()
            //GenericImplementation()
        }

    }

    @Composable
    fun ComposeLogin() {
        var email by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        var passwordVisible by remember {
            mutableStateOf(false)
        }
        Column(modifier = Modifier.padding(30.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .border(1.dp, color = Color(0xFFeff4fa), shape = RoundedCornerShape(5))
                    .fillMaxWidth()
                    .background(color = Color(0xFFeff4fa), shape = RoundedCornerShape(5))
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Jetpack Compose",
                            color = Color(0xFF01618c),
                            fontSize = 32.sp
                        )
                        Image(
                            painter = painterResource(id = R.drawable.jetpack),
                            contentDescription = "Logo of Jetpack",
                            modifier = Modifier.size(width = 130.dp, height = 130.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(text = "Login", fontSize = 35.sp, color = Color(0xFF0e6738))
                        Spacer(modifier = Modifier.height(20.dp))
                        OutlinedTextField(value = email, onValueChange = { email = it }, label = {
                            Text(
                                text = "Email ID or Mobile Number"
                            )
                        }, modifier = Modifier.fillMaxWidth())
                        Spacer(modifier = Modifier.height(20.dp))
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = {
                                Text(
                                    text = "Password"
                                )
                            }, modifier = Modifier.fillMaxWidth(),
                            trailingIcon = {
                                Icon(
                                    imageVector = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                    contentDescription = "Logo",
                                    modifier = Modifier.clickable {
                                        passwordVisible = !passwordVisible
                                    }
                                )
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
                        )
                        Row(modifier = Modifier.fillMaxWidth().padding(5.dp), horizontalArrangement = Arrangement.End) {
                            Text(text = "Forgot Password?", color=Color(0xFF166247))
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(onClick = { /*TODO*/ },colors =ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF00639a)
                        )) {
                            Text(text = "Login", fontSize = 15.sp, modifier = Modifier.padding(2.dp))
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }

                }
            }
            Row(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Row(modifier = Modifier.padding(20.dp)) {
                    Text(text = "Don't have an account?", fontSize = 15.sp)
                    Text(text = " Register", color=Color(0xFF01618c), fontSize = 15.sp)
                }
            }
        }
    }

    //View implementation
    @Composable
    fun CounterView(counterVM: CounterViewModel = viewModel()){
        val counterState = counterVM.counter.value
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment=Alignment.CenterHorizontally,
            verticalArrangement=Arrangement.Center
        ){
            Text(text="Current counter value: ${counterState.count}")
            Row{
                Button(onClick={counterVM.incrementCounter()}){
                    Text(text="increment value")
                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(onClick={counterVM.decrementCounter()}){
                    Text(text="decrement value")
                }
            }
            Button(onClick = { counterVM.resetCounter() }) {
                Text(text="Reset")
            }
        }
    }

    @Composable
    fun GenericImplementation(){
        val intContent = GenericClass(123)
        val stringContent = GenericClass("hello")
        val doubleContent = GenericClass(20.05)
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = "${intContent.content::class.simpleName} content: ${intContent.content}", fontSize = 35.sp)
            Text(text = "${stringContent.content::class.simpleName} content: ${stringContent.content}", fontSize = 35.sp)
            Text(text = "${doubleContent.content::class.simpleName} content: ${doubleContent.content}", fontSize = 35.sp)
        }
    }

}

//Implementing MVVM architecture

//Model class
data class Counter(val count:Int)

//ViewModel Class
class CounterViewModel : ViewModel(){
    private val _counter = mutableStateOf(Counter(0))
    val counter: State<Counter> = _counter  //recompose UI everytime counter changes

    fun incrementCounter(){
        _counter.value=Counter(_counter.value.count+1)
    }
    fun decrementCounter(){
        _counter.value=Counter(_counter.value.count-1)
    }
    fun resetCounter(){
        _counter.value = Counter(0);
    }
}

//Implementing Generics
class GenericClass<T>(var content:T){

    fun displaySomething(){
        println(content)
    }
}






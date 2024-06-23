package com.jangyaseni.firebaseapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.jangyaseni.firebaseapplication.ui.theme.FireBaseApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }

    val auth = FirebaseAuth.getInstance()
    fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = auth.currentUser
                    println("User: $user")
                } else {
                    println("User not created")
                    println(it.exception?.message)
                }
            }
    }

    fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = auth.currentUser
                    println("User is logged in: $user")
                } else {
                    println("User not logged in")
                    println(it.exception?.message)
                }
            }
    }

    @Composable
    fun AppNavigation(){
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "signup"){
            composable("signup"){SignUpScreen(navController)}
            composable("login"){LoginScreen(navController)}
        }
    }

    @Composable
    fun SignUpScreen(navController: NavController) {
        var email = remember {
            mutableStateOf("")
        }
        var password = remember {
            mutableStateOf("")
        }
        var passwordVisible = remember {
            mutableStateOf(false)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(10.dp).fillMaxSize()
        ) {
            Text(text = "SignUp Form:", fontSize = 20.sp)
            OutlinedTextField(value = email.value, onValueChange = {
                email.value = it
            }, label = {
                Text(text = "Enter email")
            }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(value = password.value, onValueChange = {
                password.value = it
            }, label = {
                Text(text = "Enter password")
            }, modifier = Modifier.fillMaxWidth(), trailingIcon = {
                Icon(
                    if (passwordVisible.value) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = "password",
                    modifier = Modifier.clickable {
                        passwordVisible.value = !passwordVisible.value
                    })
            },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(5.dp))
            Button(onClick = {
                signUp(email.value, password.value)
            }) {
                Text(text = "Sign Up")
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                Text(text = "Already have an account? ")
                Text(text = "Login!", modifier = Modifier.clickable {
                    navController.navigate("login")
                }, color = Color.Blue,textDecoration = TextDecoration.Underline)
            }
        }
    }

    @Composable
    fun LoginScreen(navController: NavController) {
        var email = remember {
            mutableStateOf("")
        }
        var password = remember {
            mutableStateOf("")
        }
        var passwordVisible = remember {
            mutableStateOf(false)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(10.dp).fillMaxSize()
        ) {
            Text(text = "Login Form:", fontSize=20.sp)
            OutlinedTextField(value = email.value, onValueChange = {
                email.value = it
            }, label = {
                Text(text = "Enter email")
            }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(value = password.value, onValueChange = {
                password.value = it
            }, label = {
                Text(text = "Enter password")
            }, modifier = Modifier.fillMaxWidth(), trailingIcon = {
                Icon(
                    if (passwordVisible.value) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = "password",
                    modifier = Modifier.clickable {
                        passwordVisible.value = !passwordVisible.value
                    })
            },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(5.dp))
            Button(onClick = {
                signIn(email.value, password.value)
            }) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                Text(text = "Don't have an account? ")
                Text(text = "Sign Up!", modifier = Modifier.clickable {
                    navController.navigate("signup")
                }, color = Color.Blue,textDecoration = TextDecoration.Underline)
            }
        }
    }
}


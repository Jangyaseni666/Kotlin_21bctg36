package com.jangyaseni.firebaseapplication

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.storage
import com.jangyaseni.firebaseapplication.ui.theme.FireBaseApplicationTheme
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.UUID
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            FireBaseApplicationTheme {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(10.dp),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    LinkText(text = "Click to go to Youtube", url = "https://youtube.com/")
//                }
                //WebVewScreen(url = "https://erp.silicon.ac.in/estcampus/")
                SharedPrefScreen(context = this)
            }
        }
    }

    @Composable
    fun LinkText(text: String, url: String) {
        val context = LocalContext.current
        var annotatedString = buildAnnotatedString {
            append(text)
            addStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                ),
                start = 0,
                end = text.length
            )
            addStringAnnotation(
                tag = "URL",
                annotation = url,
                start = 0,
                end = url.length
            )
        }
        ClickableText(text = annotatedString,
            onClick = { offset ->
                annotatedString.getStringAnnotations("URL", offset, offset)
                    .firstOrNull()?.let {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.item))
                        context.startActivity(intent)
                    }
            }
        )
    }

    @Composable
    fun WebVewScreen(url: String) {
        val context = LocalContext.current
        AndroidView(
            factory = {
                WebView(context).apply {
                    webViewClient = WebViewClient()
                    loadUrl(url)
                    settings.javaScriptEnabled
                }
            },
            update = {
                it.loadUrl(url)
            },
            modifier = Modifier.fillMaxSize()
        )
    }

    @Composable
    fun SharedPrefScreen(context: Context){
        val sharedPref = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        var uname by remember{
            mutableStateOf(sharedPref.getString("username", "")?:"")
        }
        var password by remember{
            mutableStateOf(sharedPref.getString("password", "")?:"")
        }
        var credsVisible by remember {
            mutableStateOf(false)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = uname, onValueChange = {
                uname=it
            }, label = {
                Text(text = "Enter username")
            })
            Spacer(modifier = Modifier.height(10.dp))
            TextField(value = password, onValueChange = {
                password=it
            }, label = {
                Text(text = "Enter password")
            })
            Button(onClick = {
                editor.putString("username", uname)
                editor.putString("password", password)
                editor.apply()
            }) {
                Text(text = "Save my creds")
            }
            Button(onClick = {
                credsVisible=!credsVisible
            }) {
                Text(text = "Show my creds")
            }
            if(credsVisible){
                Column {
                    Text(text = "Username: $uname")
                    Text(text = "Password: $password")
                }
            }
        }
    }

}


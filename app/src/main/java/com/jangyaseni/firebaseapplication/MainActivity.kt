package com.jangyaseni.firebaseapplication

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.IntentSender
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
import androidx.activity.result.IntentSenderRequest
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
import androidx.compose.material.icons.materialIcon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.google.android.gms.auth.api.identity.Identity
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
import kotlinx.coroutines.launch
import java.util.UUID
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    private val googleAuthClient by lazy {
        GoogleAuthClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            FireBaseApplicationTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "signIn"){
                    composable("signIn"){
                        val viewModel = viewModel<SignInViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()

                        LaunchedEffect(key1 = Unit) {
                            if(googleAuthClient.getSignedInUser()!=null){
                                navController.navigate("profile")
                            }
                        }

                        val launcher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.StartIntentSenderForResult(),
                            onResult = {result ->
                                if(result.resultCode == RESULT_OK){
                                    lifecycleScope.launch {
                                        val signInResult = googleAuthClient.signInWithIntent(
                                            intent = result.data?: return@launch
                                        )
                                        viewModel.onSignInResult(signInResult)
                                    }
                                }
                            }
                        )

                        LaunchedEffect(key1 = state.isSignInSuccessful) {
                            if(state.isSignInSuccessful){
                                Toast.makeText(
                                    applicationContext,
                                    "Sign In Successful",
                                    Toast.LENGTH_LONG
                                ).show()

                                navController.navigate("profile")
                                viewModel.resetState()
                            }
                        }

                        SignInScreen(
                            state = state,
                            onSignInClick = {
                                lifecycleScope.launch {
                                    val signInIntentSender = googleAuthClient.signIn()
                                    launcher.launch(
                                        IntentSenderRequest.Builder(
                                            signInIntentSender ?: return@launch
                                        ).build()
                                    )
                                }
                            }
                        )
                    }
                    composable("profile"){
                        ProfileScreen(
                            userData = googleAuthClient.getSignedInUser(),
                            onSignOut = {
                                lifecycleScope.launch {
                                    googleAuthClient.signOut()
                                    Toast.makeText(
                                        applicationContext,
                                        "Sign out successful",
                                        Toast.LENGTH_LONG
                                    ).show()

                                    navController.popBackStack()
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun SignInScreen(
        state: SignInState,
        onSignInClick: () -> Unit
    ){
        val context = LocalContext.current
        LaunchedEffect(key1 = state.signInError) {
            state.signInError?.let {error ->
                Toast.makeText(
                    context,
                    error,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = onSignInClick) {
                Text(text = "Sign In")
            }

        }
    }

    @Composable
    fun ProfileScreen(
        userData: UserData?,
        onSignOut: () -> Unit
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(userData?.username!=null){
                Text(text = "Welcome ${userData.username}!!", fontSize = 36.sp, fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = onSignOut) {
                Text(text = "Sign Out")
            }
        }

    }


}


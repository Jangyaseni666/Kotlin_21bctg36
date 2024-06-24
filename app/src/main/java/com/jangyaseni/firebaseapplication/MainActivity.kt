package com.jangyaseni.firebaseapplication

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.jangyaseni.firebaseapplication.ui.theme.FireBaseApplicationTheme
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FireBaseApplicationTheme {
                AppNavigation()
            }
        }
    }

    val auth = FirebaseAuth.getInstance()
    var storedVerificationId: String? = null
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            Log.d(TAG, "onVerificationCompleted:$credential")
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            Log.w(TAG, "onVerificationFailed", e)

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
            } else if (e is FirebaseAuthMissingActivityForRecaptchaException) {
                // reCAPTCHA verification attempted with null Activity
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken,
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Log.d(TAG, "onCodeSent:$verificationId")
            // Save verification ID and resending token so we can use them later
            storedVerificationId = verificationId
            resendToken = token
        }
    }

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
    fun AppNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "signup") {
            composable("signup") { SignUpScreen(navController) }
            composable("login") { LoginScreen(navController) }
            composable("otp") { OTPScreen(navController) }
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
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
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
                }, color = Color.Blue, textDecoration = TextDecoration.Underline)
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                Text(text = "Or Sign in with phone number", modifier = Modifier.clickable {
                    navController.navigate("otp")
                }, color = Color.Blue, textDecoration = TextDecoration.Underline)
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
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
        ) {
            Text(text = "Login Form:", fontSize = 20.sp)
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
                }, color = Color.Blue, textDecoration = TextDecoration.Underline)
            }
        }
    }

    @Composable
    fun OTPScreen(navController: NavController) {
        var phoneNumber = remember {
            mutableStateOf("")
        }
        var otp = remember {
            mutableStateOf("")
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
        ) {
            Text(text = "Sign In using Phone Number", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = phoneNumber.value, onValueChange = {
                phoneNumber.value = it
            }, label = {
                Text(text = "Enter phone number: ")
            }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone))
            Button(onClick = {
                sendOTP("+91${phoneNumber.value}")
            }) {
                Text(text = "Send OTP")
            }
            Spacer(modifier = Modifier.padding(10.dp))
            OutlinedTextField(value = otp.value, onValueChange = {
                otp.value = it
            }, label = {
                Text(text = "Enter OTP: ")
            }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone))
            Button(onClick = {
                verifyOtp(otp.value)
            }) {
                Text(text = "Verify OTP")
            }
        }
    }

    private fun verifyOtp(otp: String) {
        val credential = PhoneAuthProvider.getCredential(storedVerificationId!!, otp);
        signInWithPhoneAuthCredential(credential)
    }

    fun sendOTP(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }


    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }

}


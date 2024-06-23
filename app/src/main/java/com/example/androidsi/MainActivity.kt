package com.example.androidsi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    private val productVM: ProductViewModel by viewModels()
    private val userVM: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        val gColors = listOf(Color.Cyan, Color.Green, Color.Red)
        setContent {
            // ComposeLogin()
            //CounterView()
            //GenericImplementation()
            Column(modifier = Modifier.padding(5.dp)) {
                Text(
                    text = "Welcome to the User Gallery: ",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                //ProductGallery()
                UserGallery()
            }
        }

    }

    @Composable
    fun ProductGallery() {
        val products by productVM.products.observeAsState(emptyList())
        products.sortedBy { it.category }
        LazyVerticalGrid(columns = GridCells.Adaptive(128.dp)) {
            items(products) {
                ProductItem(product = it)
            }
        }
    }

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun ProductItem(product: Product) {
        Card(
            modifier = Modifier
                .padding(5.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(5))
                .size(height = 300.dp, width = 300.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(5.dp)
            ) {
                GlideImage(
                    model = product.image,
                    contentDescription = product.title,
                    modifier = Modifier.size(height = 100.dp, width = 100.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = product.title, fontWeight = FontWeight.Bold, maxLines = 2)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = product.description, maxLines = 2)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Category: ${product.category}", textAlign = TextAlign.Left)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Price: ${product.price}")
                Spacer(modifier = Modifier.height(5.dp))
                Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Star, contentDescription = "Rating", tint = Color.Yellow)
                    Text(text = "Rating: ${product.rating.rate}/5")
                }
            }
        }
    }

    @Composable
    fun UserGallery() {
        val users by userVM.users.observeAsState(emptyList())
        users.sortedBy { it.username}
        LazyVerticalGrid(columns = GridCells.Adaptive(128.dp)) {
            items(users) {
                UserCard(user=it)
            }
        }
    }

    @Composable
    fun UserCard(user: User){
        Card(modifier = Modifier.padding(5.dp), colors = CardDefaults.cardColors(
            containerColor = Color(0xEEC3F8E8)
        )) {
            Column(modifier = Modifier.padding(10.dp)) {
                Row {
                    Text(text = "Id: ", fontWeight = FontWeight.Bold)
                    Text(text = "${user.id}", textDecoration = TextDecoration.Underline)
                }
                Row {
                    Text(text = "Name: ", fontWeight = FontWeight.Bold)
                    Text(text = "${user.name.firstname} ${user.name.lastname}", textDecoration = TextDecoration.Underline)
                }
                Row {
                    Text(text = "Username: ", fontWeight = FontWeight.Bold)
                    Text(text = user.username, textDecoration = TextDecoration.Underline)
                }
                Row {
                    Icon(Icons.Default.Email, contentDescription = "Email", modifier = Modifier.size(20.dp, 20.dp))
                    Text(text = "Email: ", fontWeight = FontWeight.Bold)
                    Text(text = user.email, textDecoration = TextDecoration.Underline)
                }
                Row {
                    Icon(Icons.Default.Lock, contentDescription = "password", modifier = Modifier.size(20.dp, 20.dp))
                    Text(text = "Password: ", fontWeight = FontWeight.Bold)
                    Text(text = user.password, textDecoration = TextDecoration.Underline)
                }
            }
        }
    }

}

//Product Class
data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating
) {}

data class Rating(
    val rate: Double,
    val count: Int
) {}


//Product Service
interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>

    @GET("users")
    suspend fun getUsers(): List<User>
}

//Product Repository
class ProductRepo(private val apiService: ApiService) {
    suspend fun getProducts(): List<Product> {
        return apiService.getProducts()
    }
}

//Signleton class for Client
object RetrofitClient {
    private const val BASE_URL = "https://fakestoreapi.com/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

//Implementing the ViewModel
class ProductViewModel : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    private val repo = ProductRepo(RetrofitClient.apiService)

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        viewModelScope.launch {
            try {
                val productList = repo.getProducts()
                _products.postValue(productList)
//                println(productList)
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}

//User class
data class User(
    val id:Int,
    val email: String,
    val username: String,
    val password: String,
    val name: Name
)
data class Name(
    val firstname: String,
    val lastname: String
)

class UserRepo(private val apiService: ApiService){
    suspend fun getUsers():List<User>{
        return apiService.getUsers()
    }
}

class UserViewModel : ViewModel(){
    private val _users = MutableLiveData<List<User>>()
    val users:LiveData<List<User>> get() = _users

    private val repo = UserRepo(RetrofitClient.apiService)
    init {
        fetchUsers()
    }

    fun fetchUsers(){
        viewModelScope.launch {
            try {
                val userList = repo.getUsers()
                _users.postValue(userList)
                println(userList)
            }catch (e: Exception){
                println(e.message)
            }
        }
    }
}








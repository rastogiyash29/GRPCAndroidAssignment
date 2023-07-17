package com.example.grpcandroidproject

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.grpcandroidproject.navigation.setUpNavGraph
import com.example.grpcandroidproject.repository.GrpcRepository
import com.example.grpcandroidproject.ui.theme.GRPCAndroidProjectTheme
import com.kotlang.social.GetFeedRequest;

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GRPCAndroidProjectTheme {
                // A surface container using the 'background' color from the theme
                navController= rememberNavController()
                setUpNavGraph(navController = navController)
            }
        }

        loadContent()
    }

    private fun loadContent() {
        val repository = GrpcRepository.getInstance()
        val request = GetFeedRequest.newBuilder()
            .build()
        val response = repository.getFeed(request)
        Log.d("tag","response is ${response.toString()}")
// Process the response
        for (post in response.postsList) {
            Log.d("tag",post.toString())
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GRPCAndroidProjectTheme {
        Greeting("Android")
    }
}
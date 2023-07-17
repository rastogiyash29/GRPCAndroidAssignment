package com.example.grpcandroidproject.navigation
sealed class Screen(val route:String){
    object HomeScreen: Screen(route = "home_screen")
    object CommentScreen: Screen(route = "comment_screen")
    //We can add more screen routes here
}

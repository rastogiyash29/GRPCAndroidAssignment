package com.example.grpcandroidproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.grpcandroidproject.ui.screen.CommentScreen
import com.example.grpcandroidproject.ui.screen.HomeScreen

@Composable
fun setUpNavGraph(
    navController: NavHostController,
){
    NavHost(
        navController = navController
        , startDestination = Screen.HomeScreen.route
    ){
        composable(
            route = Screen.HomeScreen.route
        ){
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.CommentScreen.route
        ){
            CommentScreen(navController)
        }
    }
}
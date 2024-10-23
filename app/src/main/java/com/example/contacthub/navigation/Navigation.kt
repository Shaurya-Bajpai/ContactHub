package com.example.contacthub.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.contacthub.viewmodel.ConViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.contacthub.home.Entry
import com.example.contacthub.home.FrontView

@Composable
fun NavigationControl(
    viewModel: ConViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.homeScreen.route
    ) {
        composable(Screen.homeScreen.route){
            FrontView(viewModel,navController)
        }
        composable(Screen.addScreen.route + "/{id}" ,
                arguments = listOf(
                    navArgument("id"){
                        type = NavType.LongType
                        defaultValue = 0L
                        nullable = false
                    }
                )
        ){
            entry ->
                    val id = if(entry.arguments != null)    entry.arguments!!.getLong("id")     else    0L
            Entry(id = id, viewModel = viewModel, navController = navController)
        }
    }
}
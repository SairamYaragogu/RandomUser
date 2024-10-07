package com.user.userexplorer.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.user.userexplorer.data.User
import com.user.userexplorer.viewmodel.UserViewModel


@Composable
fun MainScreen(viewModel: UserViewModel) {
    val navController = rememberNavController()

    viewModel.fetchUsers(10)
    
    NavHost(navController = navController, startDestination = "user_list"){
        composable("user_list") {
            UserListScreen(viewModel){ selectedUser ->
                navController.currentBackStackEntry?.savedStateHandle?.set("user", selectedUser)
                navController.navigate("user_detail")
            }
        }
        composable("user_detail") { backStackEntry ->
            val user = navController.previousBackStackEntry?.savedStateHandle?.get<User>("user")
            user?.let { UserDetailScreen(it) }
        }
    }
}
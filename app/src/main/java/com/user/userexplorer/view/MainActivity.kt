package com.user.userexplorer.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.user.userexplorer.data.UserRepository
import com.user.userexplorer.viewmodel.UserViewModel
import com.user.userexplorer.viewmodel.UserViewModelFactory
import com.user.userexplorer.ui.theme.UserExplorerTheme

class MainActivity : ComponentActivity() {
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(UserRepository())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserExplorerTheme {
                MainScreen(userViewModel)
            }
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
    UserExplorerTheme {
        Greeting("Android")
    }
}
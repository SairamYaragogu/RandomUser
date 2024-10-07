package com.user.userexplorer.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.user.userexplorer.data.User
import com.user.userexplorer.viewmodel.UserViewModel

@Composable
fun UserListScreen(viewModel: UserViewModel, onUserClick: (User) -> Unit) {
    // Collect StateFlow as state
    val users by viewModel.users.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()

    // Use LaunchedEffect to call fetchUsers() when the composable first appears
    LaunchedEffect(Unit) {
        viewModel.fetchUsers(10) // Fetch 10 users
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center, // To vertically center the content in the Column
        horizontalAlignment = Alignment.CenterHorizontally // Horizontal centering
    ) {
        // Error Message Handling
        errorMessage?.let {
            Text(text = it, color = Color.Red, modifier = Modifier.padding(16.dp))
        }

        // Show loader when data is being fetched
        if (isLoading.value) {
            CircularProgressIndicator()
        } else {
            // User List
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(users) { user ->
                    UserCard(user = user, onClick = { onUserClick(user) })
                }
            }
        }

    }
}

@Composable
fun UserCard(user: User, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Image
            Image(
                painter = rememberAsyncImagePainter(model = user.picture.large),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // User Information
            Column {
                Text(text = "${user.name.first} ${user.name.last}", style = MaterialTheme.typography.h6)
                Text(text = "${user.location.street.name}, ${user.location.street.number}")
            }
        }
    }
}

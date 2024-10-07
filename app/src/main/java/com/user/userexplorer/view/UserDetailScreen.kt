package com.user.userexplorer.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.user.userexplorer.data.User

//@Preview(showBackground = true)
@Composable
fun UserDetailScreen(user: User) {
    // LazyColumn for vertical scrolling
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile Image
                Image(
                    painter = rememberAsyncImagePainter(model = user.picture.large),
                    contentDescription = null,
                    modifier = Modifier.size(250.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // User Details
                Text(text = "${user.name.first} ${user.name.last}", style = MaterialTheme.typography.h5)
                Text(text = "Address: ${user.location.street.name}, ${user.location.street.number}")

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                ){
                    // Padding inside the card
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp) // Spacing between each text
                    ){
                        DetailText(label = "City", value = "${user.location.city}")
                        DetailText(label = "State", value = "${user.location.state}")
                        DetailText(label = "Country", value = "${user.location.country}")
                        DetailText(label = "PostCode", value = "${user.location.postcode}")
                        DetailText(label = "Email", value = "${user.email}")
                        DetailText(label = "Phone", value = "${user.phone}")
                    }
                }
            }
        }
    }
}
@Composable
fun DetailText(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth() // Make the row fill the width of the screen
            .padding(8.dp) // Add padding around the row
    ) {
        // Label takes up 50% of the available space
        Text(
            text = "$label: ",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f) // Assign equal weight to the label
        )
        // Value takes up 50% of the available space
        Text(
            text = value,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f) // Assign equal weight to the value
        )
    }
}


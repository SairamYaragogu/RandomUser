package com.user.userexplorer

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performClick
import com.user.userexplorer.data.Location
import com.user.userexplorer.data.Name
import com.user.userexplorer.data.Picture
import com.user.userexplorer.data.Street
import com.user.userexplorer.data.User
import com.user.userexplorer.view.UserListScreen
import com.user.userexplorer.viewmodel.UserViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var mockRepository: MockUserRepository
    private lateinit var mockViewModel: UserViewModel

    @Before
    fun setup(){
        mockRepository = MockUserRepository()  // Initialize your mock repository

        // Create a mock ViewModel
        mockViewModel = object : UserViewModel(mockRepository) {
            override val isLoading = MutableStateFlow(true)  // Simulate loading state
            override val usersStateFlow = MutableStateFlow(emptyList<User>())  // Empty user list
        }
    }

    @Test
    fun testLoadingSpinnerVisibleWhenFetchingUsers() {
        // Set up the UI with the loading state
        composeTestRule.setContent {
            UserListScreen(
                viewModel = mockViewModel,
                onUserClick = {}
            )
        }

        // Check if the loading spinner is displayed
        composeTestRule.onNodeWithTag("loadingSpinner").assertIsDisplayed()
    }

    @Test
    fun testUserCardDisplaysCorrectInformation() {
        // Create a sample user for testing
        val sampleUsers = listOf(
            User(
                name = Name(first = "John", last = "Doe"),
                location = Location(Street(101, "First"), city = "New York", state = "NY", country = "USA", postcode = "10001"),
                email = "john.doe@example.com",
                phone = "123-456-7890",
                picture = Picture(large = "https://randomuser.me/api/portraits/men/1.jpg")
            )
        )

        // Set up the UI with a sample user list
        composeTestRule.setContent {
            UserListScreen(viewModel = mockViewModel, onUserClick = {})
        }
        // Check if the user's name is displayed
        composeTestRule.onNodeWithText("John Doe").assertIsDisplayed()

        // Check if the user's location is displayed
        composeTestRule.onNodeWithText("New York").assertIsDisplayed()

        // Check if the user's phone number is displayed
        composeTestRule.onNodeWithText("123-456-7890").assertIsDisplayed()
    }

    @Test
    fun testClickOnUserNavigatesToDetails() {
        var clickedUser: User? = null

        // Simulate a loaded state with mock users
        mockViewModel = object : UserViewModel(mockRepository) {
            override val isLoading = MutableStateFlow(false)
            override val usersStateFlow = MutableStateFlow(
                listOf(
                    User(
                        name = Name(first = "John", last = "Doe"),
                        location = Location(Street(101, "First"), city = "New York", state = "NY", country = "USA", postcode = "10001"),
                        email = "john.doe@example.com",
                        phone = "123-456-7890",
                        picture = Picture(large = "https://randomuser.me/api/portraits/men/1.jpg")
                    )
                )
            )
        }

        composeTestRule.setContent {
            UserListScreen(
                viewModel = mockViewModel,
                onUserClick = { user -> clickedUser = user }
            )
        }

        // Simulate clicking on the first user card
        composeTestRule.onNodeWithText("John Doe").performClick()

        // Verify that the correct user was passed to the click handler
        assert("${clickedUser?.name?.first} ${clickedUser?.name?.last}" == "John Doe")
    }
}


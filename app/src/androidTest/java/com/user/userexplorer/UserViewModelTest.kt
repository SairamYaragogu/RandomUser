package com.user.userexplorer

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.user.userexplorer.viewmodel.UserViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class UserViewModelTest(mockRepository: MockUserRepository) {

    private lateinit var userViewModel: UserViewModel
    private lateinit var mockRepository: MockUserRepository

    @Before
    fun setup() {
        mockRepository = MockUserRepository()  // Initialize your mock repository
        userViewModel = UserViewModel(mockRepository)  // Initialize UserViewModel with the mock repository
    }

    @Test
    fun testLoadingStateIsTrueInitially() {
        assertTrue(userViewModel.isLoading.value)
    }

    @Test
    fun testUsersStateIsEmptyInitially() {
        assertTrue(userViewModel.usersStateFlow.value.isEmpty())
    }

    @Test
    fun testFetchingUsersUpdatesTheUsersState() = runBlocking {
        // Simulate fetching users
        userViewModel.fetchUsers(5)  // Adjust the number of users as needed

        // Assert that the users state is not empty
        assertTrue(userViewModel.usersStateFlow.value.isNotEmpty())
    }

    @Test
    fun testLoadingStateIsFalseAfterFetchingUsers() = runBlocking {
        userViewModel.fetchUsers(5)  // Fetch users

        // Assert that loading state is false after fetching
        assertEquals(false, userViewModel.isLoading.value)
    }
}




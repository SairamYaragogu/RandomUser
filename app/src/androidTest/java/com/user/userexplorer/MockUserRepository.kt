package com.user.userexplorer

import com.user.userexplorer.data.UserRepository
import com.user.userexplorer.data.UserResponse
import retrofit2.Response

open class MockUserRepository : UserRepository() {
    // You can override the getUsers method here for testing
    override suspend fun getUsers(number: Int): Response<UserResponse> {
        // Return a mock response or an empty response for testing
        return Response.success(UserResponse(results = emptyList())) // Return a successful response with an empty list
    }
}

package com.user.userexplorer.data

import retrofit2.Response

open class UserRepository {
    open suspend fun getUsers(number: Int): Response<UserResponse> {
        return RetrofitInstance.api.getUsers(number)
    }
}
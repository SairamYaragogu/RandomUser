package com.user.userexplorer.viewmodel

import androidx.lifecycle.*
import com.user.userexplorer.data.User
import com.user.userexplorer.data.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class UserViewModel(private val repository: UserRepository) : ViewModel() {

    // StateFlow to hold the list of users
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    // StateFlow to hold any error messages
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _isLoading = MutableStateFlow(true)
    open val isLoading: StateFlow<Boolean> = _isLoading

    init {
        // Fetch initial users list, this could be from an API or a local database
        fetchUsers(10) // Fetching 10 users as an example
    }

    fun fetchUsers(number: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.getUsers(number)
                if (response.isSuccessful) {
                    _users.value = response.body()?.results ?: emptyList()
                } else {
                    _errorMessage.value = "Error: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load data: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    open val usersStateFlow: MutableStateFlow<List<User>> = MutableStateFlow(emptyList<User>())
}

class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(repository) as T
    }
}

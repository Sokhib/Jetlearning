package com.example.jetlearning


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel class for handling user intents and managing state in MVI architecture
 */
class UserViewModel : ViewModel() {

    // MutableStateFlow to hold the current state
    private val _state = MutableStateFlow(UserState())

    // Expose as immutable StateFlow
    val state: StateFlow<UserState> = _state.asStateFlow()

    /**
     * Process user intents and update state accordingly
     */
    fun processIntent(intent: UserIntent) {
        when (intent) {
            is UserIntent.UpdateName -> updateName(intent.name)
            is UserIntent.UpdateSurname -> updateSurname(intent.surname)
            is UserIntent.SubmitForm -> submitForm()
            is UserIntent.ResetForm -> resetForm()
        }
    }

    private fun updateName(name: String) {
        _state.value = _state.value.copy(
            userData = _state.value.userData.copy(name = name)
        )
    }

    private fun updateSurname(surname: String) {
        _state.value = _state.value.copy(
            userData = _state.value.userData.copy(surname = surname)
        )
    }

    private fun submitForm() {
        viewModelScope.launch {
            val currentUserData = _state.value.userData

            // Set loading state
            _state.value = _state.value.copy(isLoading = true)

            // Simulate network delay
            kotlinx.coroutines.delay(2000)

            if (currentUserData.isValid()) {
                // Form is valid, update state
                _state.value = _state.value.copy(
                    isLoading = false,
                    isSubmitted = true,
                    error = null
                )
            } else {
                // Form is invalid, show error
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Please fill in all fields"
                )
            }
        }
    }

    private fun resetForm() {
        _state.value = UserState()
    }
}
package com.example.jetlearning

sealed class UserIntent {
    /**
     * Intent to update the user's name
     */
    data class UpdateName(val name: String) : UserIntent()

    /**
     * Intent to update the user's surname
     */
    data class UpdateSurname(val surname: String) : UserIntent()

    /**
     * Intent to submit the form
     */
    object SubmitForm : UserIntent()

    /**
     * Intent to reset the form
     */
    object ResetForm : UserIntent()
}

data class UserState(
    val userData: UserData = UserData(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSubmitted: Boolean = false
)
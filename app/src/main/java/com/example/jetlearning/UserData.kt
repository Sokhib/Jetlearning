package com.example.jetlearning

data class UserData(
    val name: String = "",
    val surname: String = ""
) {
    /**
     * Checks if the user data is valid (both name and surname are not empty)
     */
    fun isValid(): Boolean = name.isNotBlank() && surname.isNotBlank()
}
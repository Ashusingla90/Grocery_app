package com.example.groceryapp.common

// Authentication User
sealed class AuthState {
    object Initial : AuthState()
    object CodeSent : AuthState()
    object Verified : AuthState()
    data class Error(val message: String) : AuthState()
}
package com.example.groceryapp


import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryapp.common.AuthState
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class PhoneAuthViewModel @Inject constructor() : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState =
        MutableStateFlow<AuthState>(AuthState.Initial)
    val authState: StateFlow<AuthState> = _authState

    var verificationId: String = ""
        private set

    // 🔹 Send verification code
    fun sendVerificationCode(
        phoneNumber: String,
        activity: ComponentActivity
    ) {

        val callbacks =
            object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onVerificationCompleted(
                    credential: PhoneAuthCredential
                ) {
                    signInWithCredential(credential)
                }

                override fun onVerificationFailed(
                    e: FirebaseException
                ) {
                    _authState.value =
                        AuthState.Error(e.message ?: "Verification failed")
                }

                override fun onCodeSent(
                    verId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    verificationId = verId
                    _authState.value = AuthState.CodeSent
                }
            }

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callbacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    // 🔹 Verify OTP Code
    fun verifyCode(code: String) {

        if (verificationId.isEmpty()) {
            _authState.value =
                AuthState.Error("Verification ID is empty")
            return
        }

        val credential =
            PhoneAuthProvider.getCredential(verificationId, code)

        signInWithCredential(credential)
    }

    // 🔹 Sign in with phone credential
    private fun signInWithCredential(
        credential: PhoneAuthCredential
    ) {

        viewModelScope.launch {
            try {

                auth.signInWithCredential(credential)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {
                            _authState.value =
                                AuthState.Verified
                        } else {
                            _authState.value =
                                AuthState.Error(
                                    task.exception?.message
                                        ?: "Authentication failed"
                                )
                        }
                    }

            } catch (e: Exception) {
                _authState.value =
                    AuthState.Error(
                        e.message ?: "Authentication failed"
                    )
            }
        }
    }
}

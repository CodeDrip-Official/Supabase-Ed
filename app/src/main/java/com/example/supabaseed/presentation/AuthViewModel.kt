package com.example.supabaseed.presentation

import android.content.Intent
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supabaseed.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.providers.Google
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _authUiState =
        MutableStateFlow(AuthUiState(sessionStatus = userRepository.getSessionStatus()))
    val authUiState = _authUiState.asStateFlow()

    private fun getAuthUiState(): AuthUiState {
        return _authUiState.value
    }

    fun updateAuthTextField(fieldValue: String, fieldType: FieldType) {
        val fieldMap = mapOf(FieldType.EMAIL to { state: AuthUiState, value: String ->
            state.copy(email = state.email.copy(fieldValue = value))
        }, FieldType.PASSWORD to { state: AuthUiState, value: String ->
            state.copy(password = state.password.copy(fieldValue = value))
        }, FieldType.CONF_PASSWORD to { state: AuthUiState, value: String ->
            state.copy(confirmPassword = state.confirmPassword.copy(fieldValue = value))
        })
        _authUiState.update {
            fieldMap[fieldType]?.invoke(it, fieldValue) ?: it
        }
    }

    fun updatePasswordVisibility(isVisible: Boolean, fieldType: FieldType) {
        val fieldMap = mapOf(
            FieldType.PASSWORD to { state: AuthUiState, value: Boolean ->
                state.copy(password = state.password.copy(passwordVisible = value))
            },
            FieldType.CONF_PASSWORD to { state: AuthUiState, value: Boolean ->
                state.copy(confirmPassword = state.confirmPassword.copy(passwordVisible = value))
            },
        )

        _authUiState.update {
            fieldMap[fieldType]?.invoke(it, isVisible) ?: it
        }
    }

    fun updateIsRegOrLogin() {
        _authUiState.update {
            it.copy(
                isRegister = !it.isRegister,
                email = TextFieldState(fieldType = FieldType.EMAIL),
                password = TextFieldState(fieldType = FieldType.PASSWORD),
                confirmPassword = TextFieldState(fieldType = FieldType.CONF_PASSWORD)
            )
        }
    }

    fun signOut() {
        viewModelScope.launch {
            if (userRepository.signOut()) {
                setErrorMessage(null)
            } else {
                setErrorMessage("Sign out failed")
            }
        }
    }

    fun signInWithEmail() {
        val emailValidated = validateEmail(getAuthUiState().email)
        val passwordValidated = validatePassword(getAuthUiState().password)

        if (emailValidated && passwordValidated) {

            viewModelScope.launch(Dispatchers.IO) {
                if (userRepository.signInWithEmail(
                        email = _authUiState.value.email.fieldValue,
                        password = _authUiState.value.password.fieldValue
                    )
                ) {
                    setErrorMessage(null)
                } else {
                    setErrorMessage("There was an error while logging in. Check your credentials and try again")
                }
            }
        }
    }

    fun signUpWithEmail() {
        val emailValidated = validateEmail(getAuthUiState().email)
        val passwordValidated = validatePassword(getAuthUiState().password)
        val confirmPasswordValidated = validateConfPassword(getAuthUiState().confirmPassword)

        if (emailValidated && passwordValidated && confirmPasswordValidated) {

            viewModelScope.launch(Dispatchers.IO) {
                if (userRepository.signUp(
                        email = _authUiState.value.email.fieldValue,
                        password = _authUiState.value.password.fieldValue
                    )
                ) {
                    setErrorMessage(null)
                } else {
                    setErrorMessage("There was an error while registering")
                }
            }
        }
    }

    fun signInWithGoogleTokenID(tokenID: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (userRepository.signInWithTokenID(
                    idToken = tokenID, provider = Google
                )
            ) {
                setErrorMessage(null)
            } else {
                setErrorMessage("There was an error while signing in with Google. Please try again")
            }
        }
    }

    fun signInWithFacebook() {
        viewModelScope.launch(Dispatchers.IO) {
            if (userRepository.signInWithFacebook()) {
                setErrorMessage(null)
            } else {
                setErrorMessage("There was an error while signing in with Facebook. Please try again")
            }
        }
    }

    fun handleDeeplink(intent: Intent) {
        viewModelScope.launch {
            userRepository.handleDeeplink(intent)
        }
    }

    fun resetErrorMessage() {
        setErrorMessage(null)
    }

    private fun setErrorMessage(errorMessage: String?) {
        _authUiState.update {
            it.copy(errorMessage = errorMessage)
        }
    }

    private fun validateEmail(emailFieldState: TextFieldState): Boolean {

        return if (Patterns.EMAIL_ADDRESS.matcher(emailFieldState.fieldValue).matches()) {
            true
        } else {
            _authUiState.update {
                it.copy(
                    email = emailFieldState.copy(
                        isFieldValid = false, errorMessage = "Invalid email address"
                    )
                )
            }
            false
        }
    }

    private fun validatePassword(passwordFieldState: TextFieldState): Boolean {
        Log.d("Test", passwordFieldState.fieldValue)

        return if (Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}\$").matches(passwordFieldState.fieldValue)) {
            true
        } else {
            _authUiState.update {
                it.copy(
                    password = passwordFieldState.copy(
                        isFieldValid = false, errorMessage = "Invalid password"
                    )
                )
            }
            false
        }
    }

    private fun validateConfPassword(passwordFieldState: TextFieldState): Boolean {

        return if (passwordFieldState.fieldValue == getAuthUiState().password.fieldValue) {
            true
        } else {
            _authUiState.update {
                it.copy(
                    confirmPassword = passwordFieldState.copy(
                        isFieldValid = false, errorMessage = "The password is not identical"
                    )
                )
            }
            false
        }
    }
}

data class AuthUiState(
    val isRegister: Boolean = true,
    val email: TextFieldState = TextFieldState(fieldType = FieldType.EMAIL),
    val password: TextFieldState = TextFieldState(fieldType = FieldType.PASSWORD),
    val confirmPassword: TextFieldState = TextFieldState(fieldType = FieldType.CONF_PASSWORD),
    val sessionStatus: StateFlow<SessionStatus>,
    val errorMessage: String? = null,
)

data class TextFieldState(
    val isFieldValid: Boolean = true,
    val fieldValue: String = "",
    val fieldType: FieldType,
    val passwordVisible: Boolean = false,
    val errorMessage: String = "",
)

enum class FieldType {
    EMAIL, PASSWORD, CONF_PASSWORD
}
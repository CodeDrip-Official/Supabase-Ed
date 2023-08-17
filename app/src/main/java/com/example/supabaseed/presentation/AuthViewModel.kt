package com.example.supabaseed.presentation

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AuthViewModel: ViewModel() {
    private val _authUiState =
        MutableStateFlow(AuthUiState())
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

package com.mvince.compose.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.mvince.compose.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    private val _signInFlow = MutableStateFlow<FirebaseUser?>(null)
    val signInFlow : StateFlow<FirebaseUser?> = _signInFlow

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _signInFlow.value = repository.login(email, password)
        }
    }
}
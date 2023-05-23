package com.mvince.compose.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvince.compose.domain.UserFirebase
import com.mvince.compose.repository.AuthRepository
import com.mvince.compose.repository.UserFirebaseRepository
import com.mvince.compose.ui.signin.SignUpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val firebaseRepository: UserFirebaseRepository
) : ViewModel() {

    private val _isAuthenticate = MutableStateFlow(SignUpUiState())
    val isAuthenticate: StateFlow<SignUpUiState> = _isAuthenticate

    fun signup(name: String, firstname: String, email: String, password: String) {
        viewModelScope.launch {
            val uid = authRepository.signup(name, firstname, email, password)?.uid
            if (uid != null) {
                println("c'est bon")
                val insert =
                    firebaseRepository.insertUser(uid, UserFirebase(name, firstname, email))
                println(insert)
                _isAuthenticate.update { it.copy(isSingUp = insert, isCorrect = true) }
            } else {
                _isAuthenticate.update { it.copy(isSingUp = false, isCorrect = false) }
            }
        }
    }
}
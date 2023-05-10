package com.mvince.compose.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.mvince.compose.domain.UserFirebase
import com.mvince.compose.repository.AuthRepository
import com.mvince.compose.repository.UserFirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val firebaseRepository: UserFirebaseRepository
): ViewModel() {

    private val _isAuthenticate = MutableStateFlow<Boolean>(false)
    val isAuthenticate: StateFlow<Boolean> = _isAuthenticate

    fun signup(email: String, password: String) {
        viewModelScope.launch {
            val uid  = authRepository.signup(email, password)?.uid
            if (uid != null) {
                _isAuthenticate.value = firebaseRepository.insertUser(uid, UserFirebase(email))
            }
        }
    }
}
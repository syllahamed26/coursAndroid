package com.mvince.compose.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.mvince.compose.domain.UserFirebase
import com.mvince.compose.repository.AuthRepository
import com.mvince.compose.repository.UserFirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val firebaseRepository: UserFirebaseRepository
): ViewModel() {

    private val _signInFlow = MutableStateFlow<Boolean>(false)
    val signInFlow : StateFlow<Boolean> = _signInFlow

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            val uid = repository.login(email, password)?.uid
            if (uid != null) {
                _signInFlow.update {
                    true
                }
            }
        }
    }
}
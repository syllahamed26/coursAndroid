package com.mvince.compose.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvince.compose.domain.UserFirebase
import com.mvince.compose.repository.AuthRepository
import com.mvince.compose.repository.UserFirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val firebaseRepository: UserFirebaseRepository
): ViewModel() {

    private val _isLogout = MutableStateFlow<Boolean>(false)

    val isLogout: StateFlow<Boolean>
        get() = _isLogout

    fun signOut() {
        repository.logout()
        _isLogout.update { repository.currentUser == null }
    }

    private val _user = flow<UserFirebase> {
        val user = firebaseRepository.getUserById(repository.currentUser?.uid ?: "")
        emit(user!!)
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    val user: StateFlow<UserFirebase?>
        get() = _user
}
package com.mvince.compose.ui.components.topbar

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
class TopNavigationBarViewModel @Inject constructor(
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
        firebaseRepository.getUserById(repository.currentUser?.uid ?: "")
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    val user: StateFlow<UserFirebase?>
        get() = _user
}
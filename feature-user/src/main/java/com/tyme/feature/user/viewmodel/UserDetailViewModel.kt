package com.tyme.feature.user.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tyme.core.model.UserModel
import com.tyme.core.model.decodeUserUrl
import com.tyme.core.model.toUserDetail
import com.tyme.core.repository.UserRepository
import com.tyme.feature.user.state.UserDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel
@Inject constructor(
    private val userRepository: UserRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val user: UserModel =
        checkNotNull(savedStateHandle.get<UserModel>(key = "model")?.decodeUserUrl())

    private val _uiState = MutableStateFlow(
        UserDetailUiState(
            user = user.toUserDetail()
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            userRepository.getUserDetailStream(user.id).filterNotNull().collectLatest { user ->
                _uiState.update {
                    it.copy(user = user)
                }
            }
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            updateLoadingState(true)
            userRepository.fetchUserDetail(user.username)
            updateLoadingState(false)
        }
    }

    private fun updateLoadingState(enable: Boolean) {
        _uiState.update {
            it.copy(isLoading = enable)
        }
    }
}

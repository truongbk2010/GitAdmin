package com.tyme.feature.user.state

import com.tyme.core.model.UserDetailModel


data class UserDetailUiState(
    val isLoading: Boolean = false,
    val user: UserDetailModel? = null,
)

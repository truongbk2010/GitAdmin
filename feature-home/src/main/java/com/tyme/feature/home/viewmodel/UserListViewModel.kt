package com.tyme.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.tyme.core.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserListViewModel
@Inject constructor(
    userRepository: UserRepository,
) : ViewModel() {

    val listUserPagingData = userRepository.getPagingSourceUser().cachedIn(viewModelScope)

}

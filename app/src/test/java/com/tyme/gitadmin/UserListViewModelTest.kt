package com.tyme.gitadmin

import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import com.tyme.feature.home.viewmodel.UserListViewModel
import com.tyme.gitadmin.data.model.UserModel
import com.tyme.gitadmin.data.repository.UserRepository
import com.tyme.gitadmin.utils.MainCoroutineRule
import com.tyme.gitadmin.utils.MockData.LIST_USER_MOCK
import com.tyme.gitadmin.utils.testPages
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class UserListViewModelTest {
    private lateinit var userListViewModel: UserListViewModel

    private val userRepository: UserRepository = mockk()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private fun setUpViewModel() {
        userListViewModel = UserListViewModel(userRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Given user paging data is available, when view model init, then paging data update successful`() =
        runTest {
            // Given
            val mockPagingData =
                PagingData.from(
                    data = LIST_USER_MOCK,
                    sourceLoadStates =
                        LoadStates(
                            refresh = LoadState.NotLoading(false),
                            append = LoadState.NotLoading(true),
                            prepend = LoadState.NotLoading(false),
                        ),
                )

            coEvery {
                userRepository.getPagingSourceUser()
            } returns flowOf(mockPagingData)

            // When
            setUpViewModel()

            // Then
            userListViewModel.listUserPagingData.testPages {
                val pagination = awaitPages()

                Assert.assertEquals(LIST_USER_MOCK, pagination.first())

                ignoreRemaining()
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Given user paging data is empty, when view model init, then listUserPagingData is empty`() =
        runTest {
            // Given
            val mockPagingData = PagingData.empty<UserModel>()

            coEvery {
                userRepository.getPagingSourceUser()
            } returns flowOf(mockPagingData)

            // When
            setUpViewModel()

            // Then
            userListViewModel.listUserPagingData.testPages {
                val pagination = awaitPages()

                Assert.assertTrue(pagination.isEmpty)

                ignoreRemaining()
            }
        }


}

package com.project.napptilus.ui

import app.cash.turbine.test
import arrow.core.Either
import arrow.core.right
import com.project.domain.DataWrapper
import com.project.domain.Error
import com.project.napptilus.rules.CoroutinesTestRule
import com.project.napptilus.ui.home.HomeViewModel
import com.project.napptilus.utils.sampleDataWrapper
import com.project.usecases.GetOompaLoompasByPageUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    /* Mocks */
    @Mock
    lateinit var getOompaLoompasByPageUseCase: GetOompaLoompasByPageUseCase

    /* Vars */
    private lateinit var vm: HomeViewModel
    private val dataWrapper = sampleDataWrapper

    @Before
    fun setUp() {
        val resultDataWrapper: Either<Error, DataWrapper> = dataWrapper.right()
        runTest { whenever(getOompaLoompasByPageUseCase(1)).thenReturn(resultDataWrapper) }
        vm = HomeViewModel(getOompaLoompasByPageUseCase)
    }

    @Test
    fun `State loading when fetching`() = runTest {
        vm.loadOompaLoompaItems()
        vm.state.test {
            assertEquals(HomeViewModel.UiState(isLoading = true), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `State spinnerItems and items after fetch`() = runTest {
        vm.loadOompaLoompaItems()
        vm.state.test {
            assertEquals(HomeViewModel.UiState(), awaitItem())
            val spinnerItems = awaitItem().spinnerItems
            val items = awaitItem().items
            assertEquals(HomeViewModel.UiState(isLoading = false, spinnerItems = spinnerItems, items = items), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `State stop loading after fetch`() = runTest {
        vm.loadOompaLoompaItems()
        vm.state.test {
            assertEquals(HomeViewModel.UiState(), awaitItem())
            val spinnerItems = awaitItem().spinnerItems
            val items = awaitItem().items
            assertEquals(HomeViewModel.UiState(isLoading = false, spinnerItems = spinnerItems, items = items), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}

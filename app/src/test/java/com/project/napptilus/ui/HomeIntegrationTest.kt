package com.project.napptilus.ui

import app.cash.turbine.test
import com.project.napptilus.rules.CoroutinesTestRule
import com.project.napptilus.ui.home.HomeViewModel
import com.project.napptilus.utils.buildRemoteRepository
import com.project.usecases.GetOompaLoompasByIdUseCase
import com.project.usecases.GetOompaLoompasByPageUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `get oompa loompa from remote source`() = runTest {
        val vm = buildViewModel()
        vm.loadOompaLoompaItems()

        vm.state.test {
            assertEquals(HomeViewModel.UiState(), awaitItem())
            awaitItem().spinnerItems

            val items = awaitItem().items
            assertEquals(0, items?.get(0)?.id)
            assertEquals(1, items?.get(1)?.id)
            assertEquals(2, items?.get(2)?.id)
            cancelAndIgnoreRemainingEvents()
        }
    }

    private fun buildViewModel(): HomeViewModel {
        val remoteRepository = buildRemoteRepository()
        val getOompaLoompasByPageUseCase = GetOompaLoompasByPageUseCase(remoteRepository)
        return HomeViewModel(getOompaLoompasByPageUseCase)
    }
}
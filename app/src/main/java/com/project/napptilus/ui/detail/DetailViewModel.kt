package com.project.napptilus.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.DataWrapper
import com.project.domain.Error
import com.project.domain.OompaLoompa
import com.project.napptilus.ui.home.HomeFragment
import com.project.napptilus.ui.home.HomeViewModel
import com.project.usecases.GetOompaLoompasByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getOompaLoompasByIdUseCase: GetOompaLoompasByIdUseCase): ViewModel() {

    private var item: OompaLoompa? = null

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun loadOompaLoompaItem(id: Int) {
        viewModelScope.launch {
            loading(true)
            getOompaLoompasByIdUseCase(id).fold(::onError, ::onSuccess)
            loading(false)
        }
    }
    private fun onError(error: Error) {
        _state.value = _state.value.copy(error = error)
    }

    private fun onSuccess(data: OompaLoompa) {
        item = data
        _state.value = _state.value.copy(item = item, error = null)
    }


    private fun loading(isLoading: Boolean) {
        _state.value = _state.value.copy(isLoading = isLoading)
    }

    data class UiState(
        val isLoading: Boolean = true,
        val item: OompaLoompa? = null,
        val error: Error? = null,
    )
}
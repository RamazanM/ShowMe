package com.ramazanm.showme.parent.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddConceptViewModel @Inject constructor() : ViewModel() {
    private var _uiState = MutableStateFlow(AddConceptUiState())
    val uiState = _uiState.asStateFlow()

    private var _eventFlow = MutableSharedFlow<AddConceptEvent>()
    val eventFlow = _eventFlow
    fun updateTitle(title: String) {
        _uiState.update { it.copy(title = title) }
    }
    fun updateDescription(description: String)  {
        _uiState.update { it.copy(description = description) }
    }

    fun updateImageUrl(imageUrl: String)  {
        _uiState.update { it.copy(imageUrl = imageUrl) }
    }
    fun updateSoundUrl(soundUrl: String)  {
        _uiState.update { it.copy(soundUrl = soundUrl) }
    }
    fun addConcept() {
        viewModelScope.launch {
            _eventFlow.emit(AddConceptEvent.ShowToast("Concept added"))
        }
    }
}

data class AddConceptUiState(
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val soundUrl: String = ""
)

sealed interface AddConceptEvent {
    data class ShowToast(val message: String) : AddConceptEvent
}
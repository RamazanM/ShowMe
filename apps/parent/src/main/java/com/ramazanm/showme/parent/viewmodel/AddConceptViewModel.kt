package com.ramazanm.showme.parent.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramazanm.model.Concept
import com.ramazanm.showme.data.repository.IFirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddConceptViewModel @Inject constructor(private val repository: IFirebaseRepository) :
    ViewModel() {
    private var _uiState = MutableStateFlow(AddConceptUiState())
    val uiState = _uiState.asStateFlow()

    private var _eventFlow = MutableSharedFlow<AddConceptEvent>()
    val eventFlow = _eventFlow
    fun updateTitle(title: String) {
        _uiState.update { it.copy(title = title) }
    }

    fun updateDescription(description: String) {
        _uiState.update { it.copy(description = description) }
    }

    fun updateImageUrl(index: Int, imageUrl: String) {
        _uiState.update { it.copy(imageUrls = it.imageUrls.mapIndexed { i, s -> if (i == index) imageUrl else s }) }
    }

    fun addImage() {
        _uiState.update { it.copy(imageUrls = it.imageUrls + listOf("")) }

    }

    fun deleteImage(index: Int) {
        _uiState.update { it.copy(imageUrls = it.imageUrls.filterIndexed { i, _ -> i != index }) }

    }

    fun updateSoundUrl(soundUrl: String) {
        _uiState.update { it.copy(soundUrl = soundUrl) }
    }

    fun addConcept() {
        viewModelScope.launch {
            repository.addConcept(
                Concept(
                    title = _uiState.value.title,
                    description = _uiState.value.description,
                    imageUrls = _uiState.value.imageUrls,
                    soundUrl = _uiState.value.soundUrl
                )
            )
            _eventFlow.emit(AddConceptEvent.ShowToast("Concept added"))
        }
    }
}

data class AddConceptUiState(
    val title: String = "",
    val description: String = "",
    val imageUrls: List<String> = listOf(""),
    val soundUrl: String = ""
)

sealed interface AddConceptEvent {
    data class ShowToast(val message: String) : AddConceptEvent
}
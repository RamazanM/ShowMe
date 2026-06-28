package com.ramazanm.showme.child.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ramazanm.model.Concept
import com.ramazanm.showme.child.ConceptRoute
import com.ramazanm.showme.data.repository.IFirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConceptDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: IFirebaseRepository
) : ViewModel() {

    val route = savedStateHandle.toRoute<ConceptRoute>()
    val conceptId: String = route.conceptId

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val concept = repository.getConcept(conceptId)
            _uiState.update { it.copy(concept = concept) }
        }
    }
}

data class DetailUiState(
    val concept: Concept? = null
)
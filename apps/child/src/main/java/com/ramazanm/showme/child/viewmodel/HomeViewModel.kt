package com.ramazanm.showme.child.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramazanm.model.Concept
import com.ramazanm.showme.data.repository.IFirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: IFirebaseRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()
    init {
        viewModelScope.launch {
            val concepts = repository.getConcepts()
            _uiState.update { it.copy(concepts = concepts) }
        }
    }
}

data class HomeUiState(
    val concepts: List<Concept> = emptyList()
)
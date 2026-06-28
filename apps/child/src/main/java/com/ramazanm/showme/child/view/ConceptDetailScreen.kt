package com.ramazanm.showme.child.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ramazanm.showme.child.ui.icons.arrow_back
import com.ramazanm.showme.child.viewmodel.ConceptDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConceptDetailScreen(
    viewModel: ConceptDetailViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(state.concept?.title ?: "") },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(imageVector = arrow_back, contentDescription = "Back")
                }
            }
        )
    }
    ) { innerPadding ->
        Text(
            text = "Concept Detail Screen: ${state.concept?.title}",
            modifier = Modifier.padding(innerPadding)
        )
    }
}
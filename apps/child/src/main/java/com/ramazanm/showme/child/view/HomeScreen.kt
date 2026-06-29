package com.ramazanm.showme.child.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ramazanm.showme.child.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), onSelectItem: (itemId: String) -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(title = { Text("Home") })
    }) { innerPadding ->
        val concepts = viewModel.uiState.collectAsState()

        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item { Text(text = "Home Screen") }
            items(concepts.value.concepts.filter { it.id != null }) {
                Button(onClick = { onSelectItem(it.id!!) }) { Text(it.title) }

            }
        }
    }
}
package com.ramazanm.showme.parent.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.ramazanm.showme.data.repository.TestFirebaseRepository
import com.ramazanm.showme.parent.ui.icons.add
import com.ramazanm.showme.parent.ui.icons.arrow_back
import com.ramazanm.showme.parent.ui.icons.save
import com.ramazanm.showme.parent.ui.theme.ShowMeTheme
import com.ramazanm.showme.parent.viewmodel.AddConceptEvent
import com.ramazanm.showme.parent.viewmodel.AddConceptViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddConceptScreen(viewModel: AddConceptViewModel = hiltViewModel(), onNavigateBack: () -> Unit) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is AddConceptEvent.ShowToast -> {
                    // Show toast
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(arrow_back, contentDescription = "Back")
                }
            }, title = { Text("Add Concept") },
            actions = {
                ElevatedButton(onClick = {
                    viewModel.addConcept()
                }) {
                    Icon(save, contentDescription = "Save")
                    Text(text = "Save")
                }
            }
        )
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .imePadding()
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(200.dp)
                    .border(1.dp, MaterialTheme.colorScheme.primary),
                model = uiState.imageUrls[0],
                contentDescription = "Image",
                placeholder = ColorPainter(MaterialTheme.colorScheme.secondary)
            )
            OutlinedTextField(
                value = uiState.title,
                onValueChange = viewModel::updateTitle,
                label = { Text("Title") })
            OutlinedTextField(
                value = uiState.description,
                onValueChange = viewModel::updateDescription,
                label = { Text("Description") },
                maxLines = 20,
                minLines = 5
            )
            uiState.imageUrls.forEachIndexed { index, string ->
                OutlinedTextField(
                    value = string,
                    onValueChange = { viewModel.updateImageUrl(index, it) },
                    label = { Text("Image URL") },
                    trailingIcon = {
                        if (index == 0) Icon(
                            add,
                            "",
                            modifier = Modifier.clickable { viewModel.addImage() })
                        else
                            Icon(
                                imageVector = arrow_back,
                                contentDescription = "",
                                modifier = Modifier.clickable { viewModel.deleteImage(index) })
                    })
            }
            OutlinedTextField(
                value = uiState.soundUrl,
                onValueChange = viewModel::updateSoundUrl,
                label = { Text("Sound URL") })
            Spacer(Modifier.height(16.dp))
            Button(modifier = Modifier.width(200.dp), onClick = {
                viewModel.addConcept()
            }) {
                Text(text = "Add Image")
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun AddScreenPreview() {
    ShowMeTheme {
        AddConceptScreen(
            viewModel = AddConceptViewModel(TestFirebaseRepository()),
            {})
    }
}

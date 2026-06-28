package com.ramazanm.showme.parent.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ramazanm.showme.parent.ui.icons.add
import com.ramazanm.showme.parent.ui.theme.ShowMeTheme

@Composable
fun HomeScreen(onClickAdd: () -> Unit, modifier: Modifier = Modifier) {
    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
        IconButton(onClick = onClickAdd) {
            Icon(add, contentDescription = "Add")
        }
    }) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "There will be a dashboard.")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ShowMeTheme {
        HomeScreen(onClickAdd = {})
    }
}

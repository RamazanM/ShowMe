package com.ramazanm.showme.child

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ramazanm.showme.child.ui.icons.arrow_back
import com.ramazanm.showme.child.ui.theme.ShowMeTheme
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute
@Serializable
data class ConceptRoute(val conceptId: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShowMeTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController, startDestination = HomeRoute, modifier = Modifier
                ) {
                    composable<HomeRoute> {
                        HomeScreen(
                            onSelectItem = { navController.navigate(ConceptRoute(it)) })
                    }
                    composable<ConceptRoute> {
                        ConceptDetailScreen(
                            conceptId = it.arguments?.getString("conceptId")!!,
                            onNavigateBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onSelectItem: (itemId: String) -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(arrow_back, contentDescription = "Back")
                }
            }
            , title = { Text("Home") }
        )
    }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(text = "Home Screen")
            Button(onClick = { onSelectItem("Item1") }) { Text("Item 1") }
        }
    }
}
@Composable
fun ConceptDetailScreen(conceptId: String, onNavigateBack: () -> Unit) {
    Text(text = "Concept Detail Screen: $conceptId")
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShowMeTheme {
        Greeting("Android")
    }
}
package com.ramazanm.showme.child

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ramazanm.showme.child.ui.theme.ShowMeTheme
import com.ramazanm.showme.child.view.ConceptDetailScreen
import com.ramazanm.showme.child.view.HomeScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
data class ConceptRoute(val conceptId: String)

@AndroidEntryPoint
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
                            onNavigateBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}
package com.ramazanm.showme.parent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ramazanm.showme.parent.ui.theme.ShowMeTheme
import com.ramazanm.showme.parent.view.AddConceptScreen
import com.ramazanm.showme.parent.view.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShowMeTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController, startDestination = "home", modifier = Modifier
                ) {
                    composable("home") {
                        HomeScreen(
                            onClickAdd = { navController.navigate("add_concept") })
                    }
                    composable("add_concept") {
                        AddConceptScreen(
                            onNavigateBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}
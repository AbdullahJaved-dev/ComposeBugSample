package com.sdsol.composebugsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.utils.composable
import com.sdsol.composebugsample.destinations.ScreenADestination
import com.sdsol.composebugsample.ui.theme.ComposeBugSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBugSampleTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomBar(navController) }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    )
                    {

                        NavHost(
                            navController = navController,
                            startDestination = NavGraphs.root.route
                        ) {
                            navigation(
                                route = NavGraphs.root.route,
                                startDestination = ScreenADestination.route
                            ) {

                                composable(ScreenADestination) {
                                    ScreenA()


                                    composable(ScreenADestination) {
                                        ScreenA()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun BottomBar(navController: NavController) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val route = navBackStackEntry?.destination?.route ?: ScreenADestination.route
        NavigationBar {
            BottomBarRoutes.values().forEach { destination ->
                NavigationBarItem(
                    selected = route == destination.direction.route,
                    onClick = {
                        navController.navigate(destination.direction) {
                            navController.popBackStack()
                        }
                    },
                    icon = {
                        Icon(
                            modifier = Modifier
                                .width(28.dp)
                                .wrapContentHeight(),
                            painter = painterResource(id = destination.icon),
                            contentDescription = null
                        )

                    }
                )
            }
        }
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
        ComposeBugSampleTheme {
            Greeting("Android")
        }
    }
}
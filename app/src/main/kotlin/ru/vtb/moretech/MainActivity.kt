package ru.vtb.moretech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.vtb.moretech.auth.AuthScreen
import ru.vtb.moretech.login.LoginScreen
import ru.vtb.moretech.screen.DayScreen
import ru.vtb.moretech.ui.theme.VTBTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        setContent {

            val navController = rememberAnimatedNavController()

            VTBTheme {

                AnimatedNavHost(navController, startDestination = "Registration") {
                    composable(
                        "Registration",
                        enterTransition = { initial, _ ->
                            when (initial.destination.route) {
                                "Authorization" ->
                                    slideInHorizontally(
                                        initialOffsetX = { 0 },
                                        animationSpec = tween(700)
                                    )
                                else -> null
                            }
                        },
                        exitTransition = { _, target ->
                            when (target.destination.route) {
                                "Authorization" ->
                                    slideOutHorizontally(
                                        targetOffsetX = { -1000 },
                                        animationSpec = tween(700)
                                    )
                                else -> null
                            }
                        },
                        popEnterTransition = { initial, _ ->
                            when (initial.destination.route) {
                                "Authorization" ->
                                    slideInHorizontally(
                                        initialOffsetX = { -1000 },
                                        animationSpec = tween(700)
                                    )
                                else -> null
                            }
                        },
                        popExitTransition = { _, target ->
                            when (target.destination.route) {
                                "Authorization" ->
                                    slideOutHorizontally(
                                        targetOffsetX = { 0 },
                                        animationSpec = tween(700)
                                    )
                                else -> null
                            }
                        }
                    ) { LoginScreen(navController) }
                    composable(
                        "Authorization",
                        enterTransition = { initial, _ ->
                            when (initial.destination.route) {
                                "Registration" ->
                                    slideInHorizontally(
                                        initialOffsetX = { 1000 },
                                        animationSpec = tween(700)
                                    )
                                else -> null
                            }
                        },
                        exitTransition = { _, target ->
                            when (target.destination.route) {
                                "Registration" ->
                                    slideOutHorizontally(
                                        targetOffsetX = { -1000 },
                                        animationSpec = tween(700)
                                    )
                                else -> null
                            }
                        },
                        popEnterTransition = { initial, _ ->
                            when (initial.destination.route) {
                                "Registration" ->
                                    slideInHorizontally(
                                        initialOffsetX = { -1000 },
                                        animationSpec = tween(700)
                                    )
                                else -> null
                            }
                        },
                        popExitTransition = { _, target ->
                            when (target.destination.route) {
                                "Registration" ->
                                    slideOutHorizontally(
                                        targetOffsetX = { 1000 },
                                        animationSpec = tween(700)
                                    )
                                else -> null
                            }
                        }
                    ) { AuthScreen(navController) }
                    composable("Day") {
                        DayScreen()
                    }
                    // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
////                    Greeting("Android")
////                    DayScreen()
////                    LoginScreen()
//                    AuthScreen()
//                }
                }
            }
        }
    }
}
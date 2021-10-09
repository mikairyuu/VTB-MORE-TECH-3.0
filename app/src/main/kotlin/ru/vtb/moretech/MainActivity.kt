package ru.vtb.moretech

import android.os.Bundle
import android.util.Log
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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.vtb.moretech.auth.AuthScreen
import ru.vtb.moretech.career.CareerPlanScreen
import ru.vtb.moretech.career.CareerTopBar
import ru.vtb.moretech.login.LoginScreen
import ru.vtb.moretech.screen.DayScreen
import ru.vtb.moretech.ui.theme.VTBTheme
import ru.vtb.storage.PreferencesProvider
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferencesProvider: PreferencesProvider

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var initialDestination: String

        runBlocking {
            initialDestination = preferencesProvider.getUserToken()?.let {
                if (preferencesProvider.getPassedInitialIntroduction()) {
                    "CareerPlan"
                } else {
                    "Day"
                }
            } ?: "Registration"
        }

        installSplashScreen()
        setContent {

            val navController = rememberAnimatedNavController()

            VTBTheme {

                AnimatedNavHost(navController, startDestination = initialDestination) {
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
                        DayScreen(navController)
                    }
                    composable("CareerPlan") {
                        CareerPlanScreen(navController)
                    }
                    composable("Theory") {
                        Theory(navController)
                    }
                    composable("Respect/{respectId}",
                        arguments = listOf(navArgument("respectId") { type = NavType.IntType }) ) { backStackEntry ->
                        val respectId = backStackEntry.arguments?.getInt("respectId")
                        Log.d("TESTING", respectId.toString())
                        requireNotNull(respectId)
                        Respect(respectId)
                    }
                }
            }
        }
    }
}
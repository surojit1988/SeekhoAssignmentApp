package `in`.surojit.seekhoassignmentapp.ui.view

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import `in`.surojit.seekhoassignmentapp.viewmodel.DetailViewModel
import `in`.surojit.seekhoassignmentapp.viewmodel.HomeViewModel

object Destinations {
    const val HOME = "home"
    const val DETAIL = "detail"
}

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destinations.HOME) {
        composable(Destinations.HOME) {
            val homeVm: HomeViewModel = viewModel()
            HomeScreen(homeVm, onItemClick = { id ->
                navController.navigate("${Destinations.DETAIL}/$id")
            })
        }
        composable("${Destinations.DETAIL}/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
            val detailVm: DetailViewModel = viewModel()
            DetailScreen(detailVm, animeId = id, onBack = { navController.popBackStack() })
        }
    }
}
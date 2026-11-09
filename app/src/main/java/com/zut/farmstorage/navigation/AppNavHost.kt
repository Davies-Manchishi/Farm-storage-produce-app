package com.zut.farmstorage.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zut.farmstorage.data.ProduceRepository
import com.zut.farmstorage.ui.dashboard.DashboardScreen

@Composable
fun AppNavHost(
    repository: ProduceRepository,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Routes.DASHBOARD) {
        composable(Routes.DASHBOARD) {
            DashboardScreen(
                repository = repository,
                onAddProduce = { /* Member 2's screen goes here */ },
                onViewRecords = { /* Member 3's screen goes here */ },
                onViewMonitoring = { /* Member 4's screen goes here */ }
            )
        }
    }
}

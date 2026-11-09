package com.zut.farmstorage.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zut.farmstorage.data.ProduceRepository
import com.zut.farmstorage.ui.dashboard.DashboardScreen
import com.zut.farmstorage.ui.records.RecordsScreen

@Composable
fun AppNavHost(
    repository: ProduceRepository,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.DASHBOARD
    ) {
        composable(Routes.DASHBOARD) {
            DashboardScreen(
                repository = repository,
                onAddProduce = {
                    // Member 2's screen will be connected later.
                },
                onViewRecords = {
                    navController.navigate(Routes.RECORDS)
                },
                onViewMonitoring = {
                    // Member 4's screen will be connected later.
                }
            )
        }

        composable(Routes.RECORDS) {
            RecordsScreen(
                repository = repository,
                onBack = {
                    navController.popBackStack()
                },
                onItemClick = { produceId ->
                    navController.navigate(Routes.details(produceId))
                }
            )
        }
    }
}
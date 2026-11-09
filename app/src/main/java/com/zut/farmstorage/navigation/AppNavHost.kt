package com.zut.farmstorage.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.zut.farmstorage.data.Produce
import com.zut.farmstorage.data.ProduceRepository
import com.zut.farmstorage.ui.addproduce.AddProduceScreen
import com.zut.farmstorage.ui.dashboard.DashboardScreen
import com.zut.farmstorage.ui.details.ProduceDetailsScreen
import com.zut.farmstorage.ui.records.RecordsScreen
import com.zut.farmstorage.ui.monitoring.MonitoringScreen
import kotlinx.coroutines.launch

@Composable
fun AppNavHost(repository: ProduceRepository, navController: NavHostController = rememberNavController()) {
    val scope = rememberCoroutineScope()
    
    NavHost(navController = navController, startDestination = Routes.DASHBOARD) {
        
        composable(Routes.DASHBOARD) {
            DashboardScreen(
                repository = repository,
                onAddProduce = { navController.navigate(Routes.ADD_PRODUCE) },
                onViewRecords = { navController.navigate(Routes.RECORDS) },
                onViewMonitoring = { navController.navigate(Routes.MONITORING) }
            )
        }
        
        composable(Routes.ADD_PRODUCE) {
            AddProduceScreen(onSave = { cropName, quantity, unit, date, location, notes ->
                scope.launch {
                    repository.insert(Produce(cropName = cropName, quantity = quantity, remainingQuantity = quantity, unit = unit, harvestDate = System.currentTimeMillis(), storageLocation = location, notes = notes))
                }
                navController.popBackStack()
            })
        }
        
        composable(Routes.RECORDS) {
            RecordsScreen(
                repository = repository,
                onBack = { navController.popBackStack() },
                onItemClick = { id -> navController.navigate(Routes.details(id)) }
            )
        }
        
        composable(
            route = Routes.DETAILS,
            arguments = listOf(navArgument("produceId") { type = NavType.LongType })
        ) { backStackEntry ->
            ProduceDetailsScreen(
                cropName = "Maize",
                quantity = 100.0,
                unit = "kg",
                harvestDate = "08/11/2026",
                storageLocation = "Store Room A",
                notes = "Stored in a dry and cool place",
                onBack = { navController.popBackStack() }
            )
        }
        
        composable(Routes.MONITORING) {
            MonitoringScreen(
                repository = repository,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

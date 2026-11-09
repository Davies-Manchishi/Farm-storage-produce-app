package com.zut.farmstorage.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zut.farmstorage.data.Produce
import com.zut.farmstorage.data.ProduceRepository
import com.zut.farmstorage.ui.addproduce.AddProduceScreen
import com.zut.farmstorage.ui.dashboard.DashboardScreen
import com.zut.farmstorage.ui.details.ProduceDetailsScreen
import com.zut.farmstorage.ui.records.RecordsScreen
import kotlinx.coroutines.launch

@Composable
fun AppNavHost(
    repository: ProduceRepository,
    navController: NavHostController = rememberNavController()
) {
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
            AddProduceScreen(
                onSave = { cropName, quantity, unit, date, location, notes ->
                    scope.launch {
                        repository.insert(
                            Produce(
                                cropName = cropName,
                                quantity = quantity,
                                remainingQuantity = quantity,
                                unit = unit,
                                harvestDate = System.currentTimeMillis(),
                                storageLocation = location,
                                notes = notes
                            )
                        )
                    }
                    navController.popBackStack()
                }
            )
        }
        
        composable(Routes.RECORDS) {
            RecordsScreen(
                repository = repository,
                onBack = { navController.popBackStack() }
            )
        }
        
        composable(Routes.DETAILS) {
            ProduceDetailsScreen(
                cropName = "Maize",
                quantity = 100.0,
                onBack = { navController.popBackStack() }
            )
        }
        
        composable(Routes.MONITORING) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Monitoring - Coming Soon (Member 4)")
            }
        }
    }
}

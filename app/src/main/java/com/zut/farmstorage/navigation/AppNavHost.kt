package com.zut.farmstorage.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AppNavHost(
    repository: ProduceRepository,
    navController: NavHostController = rememberNavController()
) {
    val coroutineScope = rememberCoroutineScope()

    NavHost(
        navController = navController,
        startDestination = Routes.DASHBOARD
    ) {
        composable(Routes.DASHBOARD) {
            DashboardScreen(
                repository = repository,
                onAddProduce = {
                    navController.navigate(Routes.ADD_PRODUCE)
                },
                onViewRecords = {
                    navController.navigate(Routes.RECORDS)
                },
                onViewMonitoring = {
                    // Member 4's screen will be connected later.
                }
            )
        }

        composable(Routes.ADD_PRODUCE) {
            AddProduceScreen(
                onSave = { cropName, quantity, unit, harvestDate, storageLocation, notes ->
                    coroutineScope.launch {
                        repository.insert(
                            Produce(
                                cropName = cropName,
                                quantity = quantity,
                                remainingQuantity = quantity,
                                unit = unit,
                                harvestDate = harvestDate,
                                storageLocation = storageLocation,
                                notes = notes
                            )
                        )

                        navController.popBackStack()
                    }
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

        composable(
            route = Routes.DETAILS,
            arguments = listOf(
                navArgument("produceId") {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->

            val produceId =
                backStackEntry.arguments?.getLong("produceId") ?: 0L

            val produce by repository.getById(produceId)
                .collectAsState(initial = null)

            if (produce != null) {
                val item = produce!!

                ProduceDetailsScreen(
                    cropName = item.cropName,
                    quantity = item.quantity,
                    initialRemainingQuantity = item.remainingQuantity,
                    unit = item.unit,
                    harvestDate = SimpleDateFormat(
                        "dd/MM/yyyy",
                        Locale.getDefault()
                    ).format(Date(item.harvestDate)),
                    storageLocation = item.storageLocation,
                    notes = item.notes,
                    onBack = {
                        navController.popBackStack()
                    },
                    onUpdateQuantity = { newRemainingQuantity, _ ->
                        coroutineScope.launch {
                            repository.update(
                                item.copy(
                                    remainingQuantity = newRemainingQuantity
                                )
                            )
                        }
                    }
                )
            } else {
                Text("Loading produce details...")
            }
        }
    }
}
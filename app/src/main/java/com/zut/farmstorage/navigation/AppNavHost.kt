package com.zut.farmstorage.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AppNavHost(
    repository: ProduceRepository,
    navController: NavHostController = rememberNavController()
) {

    val scope = rememberCoroutineScope()

    NavHost(
        navController = navController,
        startDestination = Routes.DASHBOARD
    ) {

        // Dashboard screen
        composable(Routes.DASHBOARD) {

            DashboardScreen(
                repository = repository,

                onAddProduce = {
                    navController.navigate(Routes.ADD_PRODUCE)
                },

                onViewRecords = {
                    // Member 3's Records screen will be connected here.
                },

                onViewMonitoring = {
                    // Member 4's Monitoring screen will be connected here.
                },

                onProduceClick = { id ->
                    navController.navigate(Routes.details(id))
                }
            )
        }

        // Add Produce screen
        composable(Routes.ADD_PRODUCE) {

            AddProduceScreen(
                onSave = {
                        cropName,
                        quantity,
                        unit,
                        harvestDate,
                        storageLocation,
                        notes ->

                    scope.launch {

                        val produce = Produce(
                            cropName = cropName,
                            quantity = quantity,
                            remainingQuantity = quantity,
                            unit = unit,
                            harvestDate = harvestDate,
                            storageLocation = storageLocation,
                            notes = notes
                        )

                        repository.insert(produce)

                        navController.popBackStack()
                    }
                }
            )
        }

        // Produce Details screen
        composable(
            route = Routes.DETAILS,
            arguments = listOf(
                navArgument("produceId") {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->

            val produceId = backStackEntry.arguments?.getLong("produceId")

            if (produceId != null) {

                val produce by repository
                    .getById(produceId)
                    .collectAsState(initial = null)

                if (produce != null) {

                    val selectedProduce = produce!!

                    val formattedDate = remember(selectedProduce.harvestDate) {
                        SimpleDateFormat(
                            "dd/MM/yyyy",
                            Locale.getDefault()
                        ).format(Date(selectedProduce.harvestDate))
                    }

                    ProduceDetailsScreen(

                        cropName = selectedProduce.cropName,

                        quantity = selectedProduce.quantity,

                        remainingQuantity = selectedProduce.remainingQuantity,

                        unit = selectedProduce.unit,

                        harvestDate = formattedDate,

                        storageLocation = selectedProduce.storageLocation,

                        notes = selectedProduce.notes,

                        onBack = {
                            navController.popBackStack()
                        },

                        onUpdateQuantity = { newRemainingQuantity, action ->

                            scope.launch {

                                repository.update(
                                    selectedProduce.copy(
                                        remainingQuantity = newRemainingQuantity
                                    )
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}
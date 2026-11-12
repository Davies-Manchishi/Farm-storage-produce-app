package com.zut.farmstorage.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zut.farmstorage.data.ProduceRepository

@Composable
fun DashboardScreen(
    repository: ProduceRepository,
    onAddProduce: () -> Unit,
    onViewRecords: () -> Unit,
    onViewMonitoring: () -> Unit
) {
    val records by repository.getAll().collectAsState(initial = emptyList())
    val totalRecords = records.size
    val totalRemaining = records.sumOf { it.remainingQuantity }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Farm Produce Storage",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            "Welcome back",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        // Summary cards
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Records",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        totalRecords.toString(),
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            Card(
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Remaining stock",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        "%.2f".format(totalRemaining),
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }

        // Navigation buttons
        Button(
            onClick = onAddProduce,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Produce")
        }

        OutlinedButton(
            onClick = onViewRecords,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Storage Records")
        }

        OutlinedButton(
            onClick = onViewMonitoring,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Storage Monitoring")
        }

        // Produce list or empty state
        if (records.isNotEmpty()) {
            Text(
                "Your produce",
                style = MaterialTheme.typography.titleMedium
            )

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(records) { record ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                record.cropName,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text(
                                "${record.remainingQuantity} ${record.unit} remaining",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "No produce registered yet. Tap 'Add Produce' to begin.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}
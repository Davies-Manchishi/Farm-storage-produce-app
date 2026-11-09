package com.zut.farmstorage.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Farm Produce Storage", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
        Text("Welcome back", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.secondary)

        Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Records", style = MaterialTheme.typography.labelMedium)
                Text(totalRecords.toString(), style = MaterialTheme.typography.headlineLarge)
            }
        }

        Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Remaining stock", style = MaterialTheme.typography.labelMedium)
                Text("%.2f".format(totalRemaining), style = MaterialTheme.typography.headlineLarge)
            }
        }

        Spacer(Modifier.height(8.dp))

        Button(onClick = onAddProduce, modifier = Modifier.fillMaxWidth()) { Text("Add Produce") }
        OutlinedButton(onClick = onViewRecords, modifier = Modifier.fillMaxWidth()) { Text("Storage Records") }
        OutlinedButton(onClick = onViewMonitoring, modifier = Modifier.fillMaxWidth()) { Text("Storage Monitoring") }

        if (records.isEmpty()) {
            Spacer(Modifier.height(16.dp))
            Text("No produce registered yet. Tap 'Add Produce' to begin.", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

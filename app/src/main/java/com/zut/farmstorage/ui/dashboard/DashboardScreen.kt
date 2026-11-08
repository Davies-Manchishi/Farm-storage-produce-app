package com.zut.farmstorage.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
        Text("Farm Produce Storage", style = MaterialTheme.typography.headlineMedium)
        Text("Welcome back", style = MaterialTheme.typography.bodyMedium)

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Records", style = MaterialTheme.typography.labelMedium)
                Text(totalRecords.toString(), style = MaterialTheme.typography.headlineLarge)
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Remaining stock", style = MaterialTheme.typography.labelMedium)
                Text("%.2f".format(totalRemaining), style = MaterialTheme.typography.headlineLarge)
            }
        }

        Spacer(Modifier.height(8.dp))

        Button(onClick = onAddProduce, enabled = false, modifier = Modifier.fillMaxWidth()) {
            Text("Add Produce (coming soon)")
        }
        OutlinedButton(onClick = onViewRecords, enabled = false, modifier = Modifier.fillMaxWidth()) {
            Text("Storage Records (coming soon)")
        }
        OutlinedButton(onClick = onViewMonitoring, enabled = false, modifier = Modifier.fillMaxWidth()) {
            Text("Storage Monitoring (coming soon)")
        }

        if (records.isEmpty()) {
            Spacer(Modifier.height(16.dp))
            Text("No produce registered yet.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

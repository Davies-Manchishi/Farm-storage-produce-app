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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

        // Updated cards to use the farming theme colors
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Records", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(totalRecords.toString(), style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.secondary)
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Remaining stock", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("%.2f".format(totalRemaining), style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.secondary)
            }
        }

        Spacer(Modifier.height(8.dp))

        // Disabled buttons are clearer now with the new theme
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
            // FORCED DARK COLOR SO IT IS ALWAYS VISIBLE
            Text(
                text = "No produce registered yet. Tap 'Add Produce' to begin.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

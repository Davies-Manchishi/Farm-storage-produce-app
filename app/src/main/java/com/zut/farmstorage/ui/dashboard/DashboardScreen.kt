package com.zut.farmstorage.ui.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.zut.farmstorage.data.ProduceRepository

private val CardGreen = Color(0xFFE8F5E9)
private val NumberGreen = Color(0xFF1B5E20)

@Composable
fun DashboardScreen(
    repository: ProduceRepository,
    onAddProduce: () -> Unit,
    onViewRecords: () -> Unit,
    onViewMonitoring: () -> Unit,
    onProduceClick: (Long) -> Unit
) {
    val records by repository.getAll().collectAsState(initial = emptyList())
    val totalRecords = records.size
    val totalRemaining = records.sumOf { it.remainingQuantity }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = "Farm Produce Storage",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Welcome back",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SummaryCard(
                label = "Records",
                value = totalRecords.toString(),
                modifier = Modifier.weight(1f)
            )
            SummaryCard(
                label = "Remaining stock",
                value = "%.2f".format(totalRemaining),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(8.dp))

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                onClick = onAddProduce,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Produce")
            }
            OutlinedButton(
                onClick = onViewRecords,
                enabled = false,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Storage Records (coming soon)")
            }
            OutlinedButton(
                onClick = onViewMonitoring,
                enabled = false,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Storage Monitoring (coming soon)")
            }
        }

        if (records.isNotEmpty()) {
            Text(
                text = "Your produce",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                records.forEach { produce ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onProduceClick(produce.produceId) },
                        colors = CardDefaults.cardColors(containerColor = CardGreen)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = produce.cropName,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "${produce.remainingQuantity} ${produce.unit} remaining",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        } else {
            Spacer(Modifier.height(8.dp))
            Text(
                text = "No produce registered yet. Tap 'Add Produce' to begin.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun SummaryCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = CardGreen)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.headlineLarge,
                color = NumberGreen
            )
        }
    }
}
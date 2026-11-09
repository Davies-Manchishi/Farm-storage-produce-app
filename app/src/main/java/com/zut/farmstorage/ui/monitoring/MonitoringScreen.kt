package com.zut.farmstorage.ui.monitoring

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zut.farmstorage.data.ProduceRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun MonitoringScreen(
    repository: ProduceRepository,
    onBack: () -> Unit
) {
    val produceList by repository.getAll().collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()

    val currentTime = System.currentTimeMillis()
    val sevenDays = 7L * 24 * 60 * 60 * 1000

    val dueProduce = produceList.filter {
        val referenceDate = it.lastInspectionDate ?: it.harvestDate
        currentTime - referenceDate >= sevenDays
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Produce Monitoring") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                text = "Inspections Due: ${dueProduce.size}",
                style = MaterialTheme.typography.titleLarge
            )

            if (dueProduce.isEmpty()) {
                Text(
                    text = "All produce inspections are up to date.",
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(dueProduce) { produce ->

                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = produce.cropName,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text("Location: ${produce.storageLocation}")

                            Text(
                                "Last inspection: ${
                                    SimpleDateFormat(
                                        "dd MMM yyyy",
                                        Locale.getDefault()
                                    ).format(
                                        Date(
                                            produce.lastInspectionDate
                                                ?: produce.harvestDate
                                        )
                                    )
                                }"
                            )

                            Button(
                                onClick = {
                                    scope.launch {
                                        repository.update(
                                            produce.copy(
                                                lastInspectionDate =
                                                    System.currentTimeMillis()
                                            )
                                        )
                                    }
                                },
                                modifier = Modifier.padding(top = 8.dp)
                            ) {
                                Text("Mark as Inspected")
                            }
                        }
                    }
                }
            }

            Button(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back")
            }
        }
    }
}
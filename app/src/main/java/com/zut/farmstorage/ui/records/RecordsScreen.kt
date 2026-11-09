package com.zut.farmstorage.ui.records

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zut.farmstorage.data.Produce
import com.zut.farmstorage.data.ProduceRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun RecordsScreen(
    repository: ProduceRepository,
    onBack: () -> Unit,
    onItemClick: (Long) -> Unit
) {
    val produceList by repository.getAll()
        .collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Storage Records",
                style = MaterialTheme.typography.headlineSmall
            )

            TextButton(onClick = onBack) {
                Text("Back")
            }
        }

        if (produceList.isEmpty()) {
            Text("No produce com.zut.farmstorage.ui.records yet. Add produce to see them here.")
        } else {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = produceList,
                    key = { it.produceId }
                ) { produce ->
                    ProduceRecordCard(produce)
                }
            }
        }
    }
}

@Composable
private fun ProduceRecordCard(produce: Produce) {
    val harvestDate = SimpleDateFormat(
        "dd MMM yyyy",
        Locale.getDefault()
    ).format(Date(produce.harvestDate))

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

            Text("Quantity: ${produce.quantity} ${produce.unit}")

            Text("Remaining: ${produce.remainingQuantity} ${produce.unit}")

            Text("Harvest date: $harvestDate")

            Text("Storage location: ${produce.storageLocation}")

            if (produce.notes.isNotBlank()) {
                Text("Notes: ${produce.notes}")
            }
        }
    }
}
package com.zut.farmstorage.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

private val CardGreen = Color(0xFFE8F5E9)

@Composable
fun ProduceDetailsScreen(
    cropName: String = "Maize",
    quantity: Double = 100.0,
    remainingQuantity: Double = quantity,
    unit: String = "kg",
    harvestDate: String = "08/11/2026",
    storageLocation: String = "Store Room A",
    notes: String = "",
    onBack: () -> Unit = {},
    onUpdateQuantity: (Double, String) -> Unit = { _, _ -> }
) {

    var currentRemainingQuantity by rememberSaveable(remainingQuantity) {
        mutableDoubleStateOf(remainingQuantity)
    }

    var adjustmentQuantity by rememberSaveable {
        mutableStateOf("")
    }

    var selectedAction by rememberSaveable {
        mutableStateOf("Sold")
    }

    var errorMessage by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Header
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = "Produce Details",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = cropName,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        // Details card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = CardGreen)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                DetailItem(label = "Crop Name", value = cropName)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    DetailItem(
                        label = "Original Quantity",
                        value = "$quantity $unit",
                        modifier = Modifier.weight(1f)
                    )
                    DetailItem(
                        label = "Remaining",
                        value = "$currentRemainingQuantity $unit",
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    DetailItem(
                        label = "Harvest Date",
                        value = harvestDate,
                        modifier = Modifier.weight(1f)
                    )
                    DetailItem(
                        label = "Storage Location",
                        value = storageLocation,
                        modifier = Modifier.weight(1f)
                    )
                }

                DetailItem(
                    label = "Notes",
                    value = notes.ifBlank { "No notes added" }
                )
            }
        }

        // Update section
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = CardGreen)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    text = "Update Produce Quantity",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Select what happened to the produce:",
                    style = MaterialTheme.typography.bodyMedium
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                    listOf("Sold", "Used", "Spoiled").forEach { action ->

                        FilterChip(
                            selected = selectedAction == action,
                            onClick = {
                                selectedAction = action
                                errorMessage = ""
                            },
                            label = { Text(action) }
                        )
                    }
                }

                OutlinedTextField(
                    value = adjustmentQuantity,
                    onValueChange = {
                        adjustmentQuantity = it
                        errorMessage = ""
                    },
                    label = { Text("Quantity ($unit)") },
                    placeholder = { Text("Enter quantity") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )

                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Button(
                    onClick = {

                        val amount = adjustmentQuantity.toDoubleOrNull()

                        errorMessage = when {

                            amount == null || amount <= 0 ->
                                "Enter a valid quantity greater than zero."

                            amount > currentRemainingQuantity ->
                                "Quantity cannot exceed available stock."

                            else -> ""
                        }

                        if (errorMessage.isEmpty() && amount != null) {

                            currentRemainingQuantity -= amount

                            onUpdateQuantity(
                                currentRemainingQuantity,
                                selectedAction
                            )

                            adjustmentQuantity = ""
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Update Quantity")
                }
            }
        }

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}

@Composable
private fun DetailItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {

        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
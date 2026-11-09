package com.zut.farmstorage.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProduceDetailsScreen(
    cropName: String = "Maize",
    quantity: Double = 100.0,
    unit: String = "kg",
    harvestDate: String = "08/11/2026",
    storageLocation: String = "Store Room A",
    notes: String = "Stored in a dry and cool place",
    onBack: () -> Unit = {},
    onUpdateQuantity: (Double, String) -> Unit = { _, _ -> }
) {

    var remainingQuantity by rememberSaveable {
        mutableDoubleStateOf(quantity)
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
            .verticalScroll(rememberScrollState())
            .padding(20.dp),

        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Produce Details",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = cropName,
            style = MaterialTheme.typography.headlineSmall
        )

        HorizontalDivider()

        DetailItem(
            label = "Crop Name",
            value = cropName
        )

        DetailItem(
            label = "Original Quantity",
            value = "$quantity $unit"
        )

        DetailItem(
            label = "Remaining Quantity",
            value = "$remainingQuantity $unit"
        )

        DetailItem(
            label = "Harvest Date",
            value = harvestDate
        )

        DetailItem(
            label = "Storage Location",
            value = storageLocation
        )

        DetailItem(
            label = "Notes",
            value = notes.ifBlank { "No notes added" }
        )

        HorizontalDivider()

        Text(
            text = "Update Produce Quantity",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "Select what happened to the produce:"
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            listOf("Sold", "Used", "Spoiled").forEach { action ->

                FilterChip(
                    selected = selectedAction == action,

                    onClick = {
                        selectedAction = action
                    },

                    label = {
                        Text(action)
                    }
                )
            }
        }

        OutlinedTextField(
            value = adjustmentQuantity,

            onValueChange = {
                adjustmentQuantity = it
                errorMessage = ""
            },

            label = {
                Text("Quantity ($unit)")
            },

            placeholder = {
                Text("Enter quantity")
            },

            modifier = Modifier.fillMaxWidth(),

            singleLine = true
        )

        if (errorMessage.isNotEmpty()) {

            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }

        Button(
            onClick = {

                val amount = adjustmentQuantity.toDoubleOrNull()

                errorMessage = when {

                    amount == null || amount <= 0 ->
                        "Enter a valid quantity greater than zero."

                    amount > remainingQuantity ->
                        "Quantity cannot exceed available stock."

                    else -> ""
                }

                if (errorMessage.isEmpty() && amount != null) {

                    remainingQuantity -= amount

                    onUpdateQuantity(
                        remainingQuantity,
                        selectedAction
                    )

                    adjustmentQuantity = ""
                }
            },

            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Update Quantity")
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
    value: String
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
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
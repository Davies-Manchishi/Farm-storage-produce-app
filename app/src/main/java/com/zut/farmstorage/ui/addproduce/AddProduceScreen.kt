package com.zut.farmstorage.ui.addproduce

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProduceScreen(
    onSave: (
        String,
        Double,
        String,
        Long,
        String,
        String
    ) -> Unit = { _, _, _, _, _, _ -> }
) {

    var cropName by rememberSaveable { mutableStateOf("") }
    var quantity by rememberSaveable { mutableStateOf("") }
    var unit by rememberSaveable { mutableStateOf("kg") }
    var storageLocation by rememberSaveable { mutableStateOf("") }
    var notes by rememberSaveable { mutableStateOf("") }
    var harvestDate by rememberSaveable { mutableLongStateOf(System.currentTimeMillis()) }
    var errorMessage by rememberSaveable { mutableStateOf("") }
    var showUnitMenu by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val calendar = remember { Calendar.getInstance() }

    val dateFormatter = remember {
        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    }

    val datePicker = remember {
        DatePickerDialog(
            context,
            { _, year, month, day ->
                calendar.set(year, month, day)
                harvestDate = calendar.timeInMillis
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
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

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = "Add Produce",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Register your harvested produce below.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        Spacer(Modifier.height(4.dp))

        OutlinedTextField(
            value = cropName,
            onValueChange = { cropName = it },
            label = { Text("Crop name") },
            placeholder = { Text("e.g. Maize") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            OutlinedTextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text("Quantity") },
                placeholder = { Text("0") },
                modifier = Modifier.weight(1f),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )

            ExposedDropdownMenuBox(
                expanded = showUnitMenu,
                onExpandedChange = { showUnitMenu = !showUnitMenu },
                modifier = Modifier.weight(1f)
            ) {

                OutlinedTextField(
                    value = unit,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Unit") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = showUnitMenu
                        )
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = showUnitMenu,
                    onDismissRequest = { showUnitMenu = false }
                ) {

                    listOf("kg", "bags", "tonnes").forEach { option ->

                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                unit = option
                                showUnitMenu = false
                            }
                        )
                    }
                }
            }
        }

        OutlinedTextField(
            value = dateFormatter.format(harvestDate),
            onValueChange = {},
            readOnly = true,
            label = { Text("Harvest date") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                TextButton(onClick = { datePicker.show() }) {
                    Text("Choose")
                }
            }
        )

        OutlinedTextField(
            value = storageLocation,
            onValueChange = { storageLocation = it },
            label = { Text("Storage location") },
            placeholder = { Text("e.g. Store room A") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notes (optional)") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(Modifier.height(4.dp))

        Button(
            onClick = {

                val enteredQuantity = quantity.toDoubleOrNull()

                errorMessage = when {

                    cropName.isBlank() ->
                        "Please enter the crop name."

                    enteredQuantity == null || enteredQuantity <= 0 ->
                        "Please enter a valid quantity greater than zero."

                    storageLocation.isBlank() ->
                        "Please enter the storage location."

                    else -> ""
                }

                if (errorMessage.isEmpty() && enteredQuantity != null) {

                    onSave(
                        cropName.trim(),
                        enteredQuantity,
                        unit,
                        harvestDate,
                        storageLocation.trim(),
                        notes.trim()
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Produce")
        }
    }
}
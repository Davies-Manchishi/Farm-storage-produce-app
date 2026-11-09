package com.zut.farmstorage.ui.monitoring

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.PeriodicWorkRequestBuilder
import androidx.compose.ui.platform.LocalContext
import com.zut.farmstorage.worker.InspectionWorker
import java.util.concurrent.TimeUnit

@Composable
fun MonitoringScreen(onBack: () -> Unit) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Storage Monitoring", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Test the background tasks and Notifications.")
        
        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            val workRequest = OneTimeWorkRequestBuilder<InspectionWorker>().build()
            WorkManager.getInstance(context).enqueue(workRequest)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Check for Inspections Now")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val workRequest = PeriodicWorkRequestBuilder<InspectionWorker>(15, TimeUnit.MINUTES).build()
            WorkManager.getInstance(context).enqueue(workRequest)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Schedule Daily Background Check")
        }

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Back to Dashboard")
        }
    }
}

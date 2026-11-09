package com.zut.farmstorage

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.zut.farmstorage.data.AppDatabase
import com.zut.farmstorage.data.RoomProduceRepository
import com.zut.farmstorage.navigation.AppNavHost
import com.zut.farmstorage.notifications.NotificationHelper
import com.zut.farmstorage.ui.theme.FarmStorageTheme
import com.zut.farmstorage.worker.InspectionWorkScheduler

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)
        val repository = RoomProduceRepository(database.produceDao())

        NotificationHelper.createNotificationChannel(this)
        InspectionWorkScheduler.schedule(this)

        if (
            Build.VERSION.SDK_INT >= 33 &&
            checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                1001
            )
        }

        setContent {
            FarmStorageTheme {
                AppNavHost(repository = repository)
            }
        }
    }
}
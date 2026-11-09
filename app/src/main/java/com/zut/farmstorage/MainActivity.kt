package com.zut.farmstorage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.zut.farmstorage.data.AppDatabase
import com.zut.farmstorage.data.RoomProduceRepository
import com.zut.farmstorage.navigation.AppNavHost
import com.zut.farmstorage.ui.theme.FarmStorageTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)
        val repository = RoomProduceRepository(database.produceDao())

        setContent {
            FarmStorageTheme {
                AppNavHost(repository = repository)
            }
        }
    }
}
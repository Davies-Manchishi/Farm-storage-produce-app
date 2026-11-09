package com.zut.farmstorage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.zut.farmstorage.data.FakeProduceRepository
import com.zut.farmstorage.navigation.AppNavHost
import com.zut.farmstorage.ui.theme.FarmStorageTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = FakeProduceRepository()
        setContent {
            FarmStorageTheme {
                var showSplash by rememberSaveable { mutableStateOf(true) }

                if (showSplash) {
                    SplashScreen(onFinished = { showSplash = false })
                } else {
                    AppNavHost(repository = repository)
                }
            }
        }
    }
}
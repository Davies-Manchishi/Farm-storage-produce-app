package com.zut.farmstorage.worker

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object InspectionWorkScheduler {

    private const val WORK_NAME = "produce_inspection_work"

    fun schedule(context: Context) {

        val workRequest =
            PeriodicWorkRequestBuilder<InspectionWorker>(
                1,
                TimeUnit.DAYS
            ).build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }
}
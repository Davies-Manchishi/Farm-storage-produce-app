package com.zut.farmstorage.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.zut.farmstorage.data.AppDatabase
import com.zut.farmstorage.notifications.NotificationHelper
import kotlinx.coroutines.flow.first

class InspectionWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val database = AppDatabase.getDatabase(applicationContext)
            val produceList = database.produceDao().getAll().first()

            val preferences = applicationContext.getSharedPreferences(
                "inspection_notifications",
                Context.MODE_PRIVATE
            )

            val currentTime = System.currentTimeMillis()
            val sevenDays = 7L * 24 * 60 * 60 * 1000

            NotificationHelper.createNotificationChannel(applicationContext)

            produceList.forEach { produce ->

                val referenceDate =
                    produce.lastInspectionDate ?: produce.harvestDate

                val isDue = currentTime - referenceDate >= sevenDays

                val notificationKey =
                    "${produce.produceId}_$referenceDate"

                if (isDue && !preferences.getBoolean(notificationKey, false)) {

                    val notificationSent = NotificationHelper.showNotification(
                        context = applicationContext,
                        notificationId = produce.produceId.toInt(),
                        cropName = produce.cropName,
                        storageLocation = produce.storageLocation
                    )

                    if (notificationSent) {
                        preferences.edit()
                            .putBoolean(notificationKey, true)
                            .apply()
                    }
                }
            }

            Result.success()

        } catch (exception: Exception) {
            Result.retry()
        }
    }
}
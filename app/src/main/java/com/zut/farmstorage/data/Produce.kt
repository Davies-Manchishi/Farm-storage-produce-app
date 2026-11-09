package com.zut.farmstorage.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "produce")
data class Produce(
    @PrimaryKey(autoGenerate = true) val produceId: Long = 0,
    val cropName: String,
    val quantity: Double,
    val remainingQuantity: Double,
    val unit: String,
    val harvestDate: Long,
    val storageLocation: String,
    val notes: String = "",
    val lastInspectionDate: Long? = null
)

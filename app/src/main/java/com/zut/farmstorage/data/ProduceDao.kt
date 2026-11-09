package com.zut.farmstorage.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProduceDao {

    @Insert
    suspend fun insert(produce: Produce): Long

    @Update
    suspend fun update(produce: Produce)

    @Delete
    suspend fun delete(produce: Produce)

    @Query("SELECT * FROM produce ORDER BY harvestDate DESC")
    fun getAll(): Flow<List<Produce>>

    @Query("SELECT * FROM produce WHERE produceId = :id")
    fun getById(id: Long): Flow<Produce?>
}




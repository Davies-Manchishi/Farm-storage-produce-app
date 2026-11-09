package com.zut.farmstorage.data

import kotlinx.coroutines.flow.Flow

interface ProduceRepository {
    fun getAll(): Flow<List<Produce>>
    fun getById(id: Long): Flow<Produce?>
    suspend fun insert(p: Produce): Long
    suspend fun update(p: Produce)
    suspend fun delete(p: Produce)
}

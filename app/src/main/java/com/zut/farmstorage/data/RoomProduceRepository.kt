package com.zut.farmstorage.data

import kotlinx.coroutines.flow.Flow

class RoomProduceRepository(private val dao: ProduceDao) : ProduceRepository {
    override fun getAll(): Flow<List<Produce>> = dao.getAll()
    override fun getById(id: Long): Flow<Produce?> = dao.getById(id)
    override suspend fun insert(p: Produce): Long = dao.insert(p)
    override suspend fun update(p: Produce) = dao.update(p)
    override suspend fun delete(p: Produce) = dao.delete(p)
}

package com.zut.farmstorage.data

import kotlinx.coroutines.flow.Flow

class RoomProduceRepository(
    private val produceDao: ProduceDao
) : ProduceRepository {

    override fun getAll(): Flow<List<Produce>> {
        return produceDao.getAll()
    }

    override fun getById(id: Long): Flow<Produce?> {
        return produceDao.getById(id)
    }

    override suspend fun insert(p: Produce): Long {
        return produceDao.insert(p)
    }

    override suspend fun update(p: Produce) {
        produceDao.update(p)
    }

    override suspend fun delete(p: Produce) {
        produceDao.delete(p)
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> origin/member-4-notifications-workmanager

package com.zut.farmstorage.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class FakeProduceRepository : ProduceRepository {

    private val _records = MutableStateFlow<List<Produce>>(emptyList())

    private var nextId = 1L

    // Get all produce records
    override fun getAll(): Flow<List<Produce>> =
        _records.asStateFlow()

    // Observe one produce record by its ID
    override fun getById(id: Long): Flow<Produce?> =
        _records.map { records ->
            records.find { it.produceId == id }
        }

    // Add a new produce record
    override suspend fun insert(p: Produce): Long {
        val newProduce = p.copy(produceId = nextId++)

        _records.value = _records.value + newProduce

        return newProduce.produceId
    }

    // Update an existing produce record
    override suspend fun update(p: Produce) {
        _records.value = _records.value.map { record ->
            if (record.produceId == p.produceId) p else record
        }
    }

    // Delete a produce record
    override suspend fun delete(p: Produce) {
        _records.value = _records.value.filter { record ->
            record.produceId != p.produceId
        }
    }
}
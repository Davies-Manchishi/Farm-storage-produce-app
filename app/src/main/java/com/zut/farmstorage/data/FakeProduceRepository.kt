package com.zut.farmstorage.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeProduceRepository : ProduceRepository {
    private val _records = MutableStateFlow<List<Produce>>(emptyList())
    private var nextId = 1L

    override fun getAll(): Flow<List<Produce>> = _records.asStateFlow()

    override fun getById(id: Long): Flow<Produce?> =
        MutableStateFlow(_records.value.find { it.produceId == id }).asStateFlow()

    override suspend fun insert(p: Produce): Long {
        val new = p.copy(produceId = nextId++)
        _records.value = _records.value + new
        return new.produceId
    }

    override suspend fun update(p: Produce) {
        _records.value = _records.value.map { if (it.produceId == p.produceId) p else it }
    }

    override suspend fun delete(p: Produce) {
        _records.value = _records.value.filter { it.produceId != p.produceId }
    }
}

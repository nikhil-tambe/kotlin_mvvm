package com.nikhil.suven.ui.add_purchase

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.nikhil.suven.app.db.transaction.CacheTransactionMapper
import com.nikhil.suven.app.db.transaction.TransactionDao
import com.nikhil.suven.app.domain_model.Transaction
import com.nikhil.suven.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Works as a repository for data transaction over Network or Local cache.
 * */
class PurchaseRepository
constructor(
    private val transactionDao: TransactionDao,
    private val cacheEntityMapper: CacheTransactionMapper
) {

    suspend fun addNewGoal(transaction: Transaction): Flow<DataState<String>> = flow {
        emit(DataState.Loading)
        transactionDao.insert(cacheEntityMapper.mapToEntity(transaction))
        emit(DataState.Success("Transaction saved successfully"))
    }

    fun getTotalPurchaseCount(): LiveData<Int> {
        return transactionDao.getAllPurchaseCount()
    }

    fun getAllPurchasesLive() = liveData <List<Transaction>>{
        emitSource(
            transactionDao.getAllPurchasesLive()
                .map {
                    cacheEntityMapper.mapFromEntityList(it)
                }
        )

    }

}
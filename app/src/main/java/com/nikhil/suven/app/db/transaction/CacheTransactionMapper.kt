package com.nikhil.suven.app.db.transaction

import com.nikhil.suven.app.domain_model.Transaction
import com.nikhil.suven.utils.EntityMapper
import java.util.*
import javax.inject.Inject

/**
 * Utility class to map GoalsEntity to Goal and vice versa.
 */
class CacheTransactionMapper
@Inject
constructor() : EntityMapper<TransactionEntity, Transaction> {

    override fun mapFromEntity(entity: TransactionEntity): Transaction {
        return Transaction(
            dateCreated = entity.dateCreated,
            forWhom = entity.forWhom,
            isVariable = entity.isVariable,
            dateOfPurchase = entity.dateOfPurchase,
            numberOfUnit = entity.numberOfUnit,
            purchasePrice = entity.purchasePrice
        )
    }

    override fun mapToEntity(model: Transaction): TransactionEntity {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return TransactionEntity(
            forWhom = model.forWhom,
            isVariable = model.isVariable,
            dateOfPurchase = calendar.timeInMillis,
            numberOfUnit = model.numberOfUnit,
            purchasePrice = model.purchasePrice
        )
    }

    fun mapFromEntityList(list: List<TransactionEntity>): List<Transaction> {
        return list.map { mapFromEntity(it) }
    }

}
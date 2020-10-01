package com.nikhil.suven.app.db.transaction

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.nikhil.suven.app.db.BaseDao

@Dao
abstract class TransactionDao : BaseDao<TransactionEntity> {

    @Query("select * from transactions")
    abstract fun getAllPurchasesLive(): LiveData<List<TransactionEntity>>

    @Query("select * from transactions")
    abstract fun getAllPurchases(): List<TransactionEntity>

    @Query("select count(*) from transactions")
    abstract fun getAllPurchaseCount(): LiveData<Int>

}
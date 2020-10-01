package com.nikhil.suven.app.db.goals

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.nikhil.suven.app.db.BaseDao

@Dao
abstract class GoalsDao : BaseDao<GoalsEntity> {

    @Query("select * from goals")
    abstract fun getAllPurchasesLive(): LiveData<List<GoalsEntity>>

    @Query("select * from goals")
    abstract fun getAllPurchases(): List<GoalsEntity>

    @Query("select count(*) from goals")
    abstract fun getAllPurchaseCount(): LiveData<Int>

}
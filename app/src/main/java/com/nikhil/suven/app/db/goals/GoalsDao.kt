package com.nikhil.suven.app.db.goals

import androidx.room.Dao
import androidx.room.Query
import com.nikhil.suven.app.db.BaseDao

@Dao
abstract class GoalsDao : BaseDao<GoalsEntity> {

    @Query("select * from goals")
    abstract fun getAllMessages(): List<GoalsEntity>

    @Query("select * from goals where for_whom =:forWhom and is_fixed=:isFixed and date_of_purchase=:dateOfPurchase and number_of_unit =:numberOfUnit and purchase_price =:purchasePrice")
    abstract fun getGoalDuplicate(
        forWhom: Int,
        isFixed: Boolean,
        dateOfPurchase: Long,
        numberOfUnit: Int,
        purchasePrice: Float
    ): GoalsEntity?

}
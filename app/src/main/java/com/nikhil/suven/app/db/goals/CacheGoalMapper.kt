package com.nikhil.suven.app.db.goals

import com.nikhil.suven.app.domain_model.Goal
import com.nikhil.suven.utils.EntityMapper
import java.util.*
import javax.inject.Inject

/**
 * Utility class to map GoalsEntity to Goal and vice versa.
 */
class CacheGoalMapper
@Inject
constructor() : EntityMapper<GoalsEntity, Goal> {

    override fun mapFromEntity(entity: GoalsEntity): Goal {
        return Goal(
            dateCreated = entity.dateCreated,
            forWhom = entity.forWhom,
            isVariable = entity.isVariable,
            dateOfPurchase = entity.dateOfPurchase,
            numberOfUnit = entity.numberOfUnit,
            purchasePrice = entity.purchasePrice
        )
    }

    override fun mapToEntity(model: Goal): GoalsEntity {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return GoalsEntity(
            forWhom = model.forWhom,
            isVariable = model.isVariable,
            dateOfPurchase = calendar.timeInMillis,
            numberOfUnit = model.numberOfUnit,
            purchasePrice = model.purchasePrice
        )
    }

    fun mapFromEntityList(list: List<GoalsEntity>): List<Goal> {
        return list.map { mapFromEntity(it) }
    }

}
package com.nikhil.suven.ui.goals

import com.nikhil.suven.app.db.goals.GoalsDao
import com.nikhil.suven.app.domain_model.Goal
import com.nikhil.suven.utils.DataState
import com.nikhil.suven.app.db.goals.CacheGoalMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

/**
 * Works as a repository for data transaction over Network or Local cache.
 * */
class GoalsRepository
constructor(
    private val goalsDao: GoalsDao,
    private val cacheEntityMapper: CacheGoalMapper
){

    suspend fun addNewGoal(goal: Goal): Flow<DataState<String>> = flow {
        emit(DataState.Loading)

        val duplicateGoal = goalsDao.getGoalDuplicate(
            goal.forWhom,
            goal.isFixed,
            goal.dateOfPurchase,
            goal.numberOfUnit,
            goal.purchasePrice
        )
        duplicateGoal?.let {
            emit(DataState.Error(DuplicateFormatFlagsException("Duplicate Entry Exists")))
        }

        goalsDao.insert(cacheEntityMapper.mapToEntity(goal))

        emit(DataState.Success("Goal saved successfully"))
    }

}
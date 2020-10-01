package com.nikhil.suven.ui.goals

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.nikhil.suven.app.db.goals.CacheGoalMapper
import com.nikhil.suven.app.db.goals.GoalsDao
import com.nikhil.suven.app.db.goals.GoalsEntity
import com.nikhil.suven.app.domain_model.Goal
import com.nikhil.suven.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Works as a repository for data transaction over Network or Local cache.
 * */
class GoalsRepository
constructor(
    private val goalsDao: GoalsDao,
    private val cacheEntityMapper: CacheGoalMapper
) {

    suspend fun addNewGoal(goal: Goal): Flow<DataState<String>> = flow {
        emit(DataState.Loading)
        goalsDao.insert(cacheEntityMapper.mapToEntity(goal))
        emit(DataState.Success("Goal saved successfully"))
    }

    fun getTotalPurchaseCount(): LiveData<Int> {
        return goalsDao.getAllPurchaseCount()
    }

    fun getAllPurchasesLive() = liveData <List<Goal>>{
        emitSource(
            goalsDao.getAllPurchasesLive()
                .map {
                    cacheEntityMapper.mapFromEntityList(it)
                }
        )

    }

}
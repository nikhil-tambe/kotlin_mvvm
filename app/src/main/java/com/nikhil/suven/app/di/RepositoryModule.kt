package com.nikhil.suven.app.di

import com.nikhil.suven.app.db.goals.CacheGoalMapper
import com.nikhil.suven.app.db.goals.GoalsDao
import com.nikhil.suven.app.db.messages.CacheMessageMapper
import com.nikhil.suven.app.db.messages.MessageDao
import com.nikhil.suven.ui.chat.ChatRepository
import com.nikhil.suven.ui.goals.GoalsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideGoalsRepository(
        goalsDao: GoalsDao,
        cacheGoalMapper: CacheGoalMapper
    ): GoalsRepository {
        return GoalsRepository(
            goalsDao = goalsDao,
            cacheEntityMapper = cacheGoalMapper
        )
    }

    @Provides
    fun provideChatRepository(
        messageDao: MessageDao,
        cacheMessageMapper: CacheMessageMapper
    ): ChatRepository {
        return ChatRepository(
            messageDao = messageDao,
            cacheMessageMapper = cacheMessageMapper
        )
    }

}
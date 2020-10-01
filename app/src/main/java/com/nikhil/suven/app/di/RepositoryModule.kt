package com.nikhil.suven.app.di

import com.nikhil.suven.app.db.transaction.CacheTransactionMapper
import com.nikhil.suven.app.db.transaction.TransactionDao
import com.nikhil.suven.app.db.messages.CacheMessageMapper
import com.nikhil.suven.app.db.messages.MessageDao
import com.nikhil.suven.ui.chat.ChatRepository
import com.nikhil.suven.ui.add_purchase.PurchaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideGoalsRepository(
        transactionDao: TransactionDao,
        cacheTransactionMapper: CacheTransactionMapper
    ): PurchaseRepository {
        return PurchaseRepository(
            transactionDao = transactionDao,
            cacheEntityMapper = cacheTransactionMapper
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
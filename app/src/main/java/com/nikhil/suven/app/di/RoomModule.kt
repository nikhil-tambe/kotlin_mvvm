package com.nikhil.suven.app.di

import android.content.Context
import androidx.room.Room
import com.nikhil.suven.app.db.FintechDB
import com.nikhil.suven.app.db.transaction.TransactionDao
import com.nikhil.suven.app.db.messages.MessageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): FintechDB {
        return Room.databaseBuilder(context, FintechDB::class.java, FintechDB.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideGoalsDao(fintechDB: FintechDB): TransactionDao {
        return fintechDB.transactionDao
    }

    @Singleton
    @Provides
    fun provideMessageDao(fintechDB: FintechDB): MessageDao {
        return fintechDB.messageDao
    }

}
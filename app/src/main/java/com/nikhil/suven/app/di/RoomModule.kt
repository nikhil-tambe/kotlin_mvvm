package com.nikhil.suven.app.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nikhil.suven.app.db.FintechDB
import com.nikhil.suven.app.db.goals.GoalsDao
import com.nikhil.suven.app.db.messages.MessageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.coroutineScope
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
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
    fun provideGoalsDao(fintechDB: FintechDB): GoalsDao {
        return fintechDB.goalsDao
    }

    @Singleton
    @Provides
    fun provideMessageDao(fintechDB: FintechDB): MessageDao {
        return fintechDB.messageDao
    }

}
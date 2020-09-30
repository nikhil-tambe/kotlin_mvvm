package com.nikhil.suven.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikhil.suven.app.db.FintechDB.Companion.DB_VERSION
import com.nikhil.suven.app.db.goals.GoalsDao
import com.nikhil.suven.app.db.goals.GoalsEntity
import com.nikhil.suven.app.db.messages.MessageDao
import com.nikhil.suven.app.db.messages.MessageEntity

@Database(
    entities = [GoalsEntity::class, MessageEntity::class],
    version = DB_VERSION,
    exportSchema = false
)
abstract class FintechDB : RoomDatabase() {

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "fintech_db"
    }

    abstract val goalsDao: GoalsDao

    abstract val messageDao: MessageDao

}
package com.mii.techincaltest.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mii.techincaltest.app.data.local.dao.AccountDao
import com.mii.techincaltest.app.data.local.dao.TransactionDao
import com.mii.techincaltest.app.data.local.entity.AccountEntity
import com.mii.techincaltest.app.data.local.entity.TransactionEntity

@Database(entities = [AccountEntity::class, TransactionEntity::class], exportSchema = false, version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun accountDao(): AccountDao
    abstract fun transactionDao(): TransactionDao

}
package com.mii.techincaltest.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mii.techincaltest.app.data.local.entity.AccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun initAccount(accountEntity: AccountEntity)

    @Query("SELECT * FROM accounts WHERE id = :id")
    fun getAccount(id: String): Flow<AccountEntity>

    @Query("UPDATE accounts SET balance = :balance WHERE id = :id")
    suspend fun updateBalance(balance: Int, id: String)

}
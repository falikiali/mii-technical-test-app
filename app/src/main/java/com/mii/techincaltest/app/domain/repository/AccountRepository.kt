package com.mii.techincaltest.app.domain.repository

import com.mii.techincaltest.app.domain.model.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    suspend fun initAccount(account: Account)
    suspend fun updateBalance(balance: Int, id: String)
    fun getAccount(id: String): Flow<Account>

}
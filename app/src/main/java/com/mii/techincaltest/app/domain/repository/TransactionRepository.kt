package com.mii.techincaltest.app.domain.repository

import com.mii.techincaltest.app.domain.model.Transaction
import com.mii.techincaltest.app.helper.ResultState
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    suspend fun addNewTransaction(transaction: Transaction, currentBalance: Int): Result<Transaction>
    fun getTransactions(): Flow<List<Transaction>>

}
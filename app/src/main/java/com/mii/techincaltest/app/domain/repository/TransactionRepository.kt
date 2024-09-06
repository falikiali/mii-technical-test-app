package com.mii.techincaltest.app.domain.repository

import com.mii.techincaltest.app.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    suspend fun addNewTransaction(transaction: Transaction)
    fun getTransactions(): Flow<List<Transaction>>

}
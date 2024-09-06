package com.mii.techincaltest.app.data

import com.mii.techincaltest.app.data.local.dao.TransactionDao
import com.mii.techincaltest.app.domain.model.Transaction
import com.mii.techincaltest.app.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(private val transactionDao: TransactionDao): TransactionRepository {
    override suspend fun addNewTransaction(transaction: Transaction) {
        transactionDao.addNewTransaction(transaction.toEntity())
    }

    override fun getTransactions(): Flow<List<Transaction>> {
        return transactionDao.getTransactions().map { list ->
            list.map { it.toDomain() }
        }
    }
}
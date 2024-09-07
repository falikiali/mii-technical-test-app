package com.mii.techincaltest.app.data

import android.database.sqlite.SQLiteConstraintException
import com.mii.techincaltest.app.data.local.dao.AccountDao
import com.mii.techincaltest.app.data.local.dao.TransactionDao
import com.mii.techincaltest.app.domain.model.Transaction
import com.mii.techincaltest.app.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(private val transactionDao: TransactionDao, private val accountDao: AccountDao): TransactionRepository {
    override suspend fun addNewTransaction(transaction: Transaction, currentBalance: Int): Result<Transaction> {
        return try {
            val resultBalance = currentBalance - transaction.amount
            transactionDao.addNewTransaction(transaction.toEntity())
            accountDao.updateBalance(resultBalance, "00001")
            Result.success(transaction)
        } catch (e: SQLiteConstraintException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getTransactions(): Flow<List<Transaction>> {
        return transactionDao.getTransactions().map { list ->
            list.map { it.toDomain() }
        }
    }
}
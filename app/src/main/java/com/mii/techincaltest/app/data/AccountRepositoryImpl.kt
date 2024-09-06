package com.mii.techincaltest.app.data

import com.mii.techincaltest.app.data.local.dao.AccountDao
import com.mii.techincaltest.app.domain.model.Account
import com.mii.techincaltest.app.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(private val accountDao: AccountDao): AccountRepository {
    override suspend fun initAccount(account: Account) {
        accountDao.initAccount(account.toEntity())
    }

    override suspend fun updateBalance(balance: Int, id: String) {
        accountDao.updateBalance(balance, id)
    }

    override fun getAccount(id: String): Flow<Account> {
        return accountDao.getAccount(id).map { it.toDomain() }
    }
}
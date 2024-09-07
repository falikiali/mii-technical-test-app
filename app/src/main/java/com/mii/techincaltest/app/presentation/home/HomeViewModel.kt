package com.mii.techincaltest.app.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mii.techincaltest.app.domain.model.Account
import com.mii.techincaltest.app.domain.model.Transaction
import com.mii.techincaltest.app.domain.repository.AccountRepository
import com.mii.techincaltest.app.domain.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val accountRepository: AccountRepository, private val transactionRepository: TransactionRepository): ViewModel() {

    private val _resultAccount = MutableLiveData<Account>(null)
    val resultAccount: LiveData<Account> get() = _resultAccount

    private val _resultHistoryTransaction = MutableLiveData<List<Transaction>>(emptyList())
    val resultHistoryTransaction: LiveData<List<Transaction>> get() = _resultHistoryTransaction

    init {
        initAccount()
    }

    private fun initAccount() {
        viewModelScope.launch {
            accountRepository.initAccount(
                Account(
                    "00001",
                    500000000,
                    "John Doe"
                )
            )
        }
    }

    fun getAccount() {
        viewModelScope.launch {
            accountRepository.getAccount("00001").collect {
                _resultAccount.postValue(it)
            }
        }
    }

    fun getHistoryTransaction() {
        viewModelScope.launch {
            transactionRepository.getTransactions().collect {
                _resultHistoryTransaction.postValue(it)
            }
        }
    }

    suspend fun addNewTransaction(id: String, merchant: String, amount: Int, currentBalance: Int): Result<Transaction> {
        val transaction = Transaction(
            id, merchant, amount
        )

        return transactionRepository.addNewTransaction(transaction, currentBalance)
    }

}
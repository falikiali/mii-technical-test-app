package com.mii.techincaltest.app.domain.model

import com.mii.techincaltest.app.data.local.entity.TransactionEntity

data class Transaction(
    val id: String,
    val merchantName: String,
    val amount: Int
) {
    fun toEntity(): TransactionEntity {
        return TransactionEntity(id, merchantName, amount)
    }
}

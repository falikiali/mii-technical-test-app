package com.mii.techincaltest.app.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.mii.techincaltest.app.domain.model.Transaction

@Entity(tableName = "transactions")
data class TransactionEntity(
    @ColumnInfo("id") val id: String,
    @ColumnInfo("merchant_name") val merchantName: String,
    @ColumnInfo("amount") val amount: Int
) {
    fun toDomain(): Transaction {
        return Transaction(id, merchantName, amount)
    }
}

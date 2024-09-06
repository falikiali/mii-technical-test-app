package com.mii.techincaltest.app.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mii.techincaltest.app.domain.model.Account

@Entity(tableName = "accounts")
data class AccountEntity(
    @PrimaryKey val id: String,
    @ColumnInfo("balance") val balance: Int,
    @ColumnInfo("balance") val name: String
) {
    fun toDomain(): Account {
        return Account(id, balance, name)
    }
}

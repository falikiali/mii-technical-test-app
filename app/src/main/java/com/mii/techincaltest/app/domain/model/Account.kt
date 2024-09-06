package com.mii.techincaltest.app.domain.model

import com.mii.techincaltest.app.data.local.entity.AccountEntity

data class Account(
    val id: String,
    val balance: Int,
    val name: String
) {
    fun toEntity(): AccountEntity {
        return AccountEntity(id, balance, name)
    }
}

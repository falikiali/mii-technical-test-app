package com.mii.techincaltest.app.di

import com.mii.techincaltest.app.data.AccountRepositoryImpl
import com.mii.techincaltest.app.data.PromoRepositoryImpl
import com.mii.techincaltest.app.data.TransactionRepositoryImpl
import com.mii.techincaltest.app.domain.repository.AccountRepository
import com.mii.techincaltest.app.domain.repository.PromoRepository
import com.mii.techincaltest.app.domain.repository.TransactionRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Provides
    abstract fun provideAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository

    @Binds
    @Provides
    abstract fun provideTransactionRepository(transactionRepositoryImpl: TransactionRepositoryImpl): TransactionRepository

    @Binds
    @Provides
    abstract fun providePromoRepository(promoRepositoryImpl: PromoRepositoryImpl): PromoRepository

}
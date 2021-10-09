package ru.vtb.message.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.vtb.message.data.api.DayApi
import ru.vtb.message.data.repository.DayRepositoryImpl
import ru.vtb.message.domain.repository.DayRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DayModule {

    @Binds
    abstract fun bindDayRepository(repository: DayRepositoryImpl): DayRepository
}


@Module
@InstallIn(SingletonComponent::class)
object DayApiModule {

    @Provides
    @Singleton
    fun provideDayApi(retrofit: Retrofit): DayApi = retrofit.create(DayApi::class.java)
}
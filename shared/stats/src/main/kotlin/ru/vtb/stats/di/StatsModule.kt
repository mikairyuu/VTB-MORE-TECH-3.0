package ru.vtb.stats.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.vtb.stats.data.api.StatsApi
import ru.vtb.stats.data.repository.StatsRepositoryImpl
import ru.vtb.stats.domain.repository.StatsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface StatsModule {

    @Binds
    abstract fun bindStatsRepository(repository: StatsRepositoryImpl): StatsRepository
}


@Module
@InstallIn(SingletonComponent::class)
object StatsApiModule {

    @Provides
    @Singleton
    fun provideStatsApi(retrofit: Retrofit): StatsApi = retrofit.create(StatsApi::class.java)
}
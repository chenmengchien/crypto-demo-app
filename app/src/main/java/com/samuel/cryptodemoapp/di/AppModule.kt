package com.samuel.cryptodemoapp.di

import android.app.Application
import androidx.room.Room
import com.samuel.cryptodemoapp.data.local.CurrencyDatabase
import com.samuel.cryptodemoapp.data.remote.CurrencyService
import com.samuel.cryptodemoapp.data.repository.CurrencyRepositoryImpl
import com.samuel.cryptodemoapp.domain.repository.CurrencyRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCurrencyListRepository(
        db: CurrencyDatabase,
        service: CurrencyService
    ): CurrencyRepository {
        return CurrencyRepositoryImpl(service = service, dao = db.currencyDao())
    }

    @Provides
    @Singleton
    fun provideCurrencyDatabase(app: Application): CurrencyDatabase {
        return Room.databaseBuilder(
            app, CurrencyDatabase::class.java, "currency_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCurrencyService(app: Application): CurrencyService {
        return Retrofit.Builder()
            .baseUrl("https://baseurl/")
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build()
            )
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .addLast(KotlinJsonAdapterFactory())
                        .build()
                ).asLenient()
            )
            .build()
            .create(CurrencyService::class.java)
    }
}

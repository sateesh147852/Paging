package com.paging.di

import com.paging.network.QuoteApiService
import com.paging.paging.QuotePagingAdapter
import com.paging.paging.QuotePagingSource
import com.paging.repository.QuoteRepository
import com.paging.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PagingDiModule {

    @Provides
    fun providerQuoteAdapter(): QuotePagingAdapter = QuotePagingAdapter()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): QuoteApiService {
        return retrofit.create(QuoteApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideQuotePagingSource(apiService: QuoteApiService): QuotePagingSource {
        return QuotePagingSource(apiService)
    }

    @Singleton
    @Provides
    fun provideQuoteRepository(quotePagingSource: QuotePagingSource): QuoteRepository {
        return QuoteRepository(quotePagingSource)
    }
}
package com.example.satellitesinspace.di

import com.example.satellitesinspace.common.AppUtil
import com.example.satellitesinspace.common.MockInterceptor
import com.example.satellitesinspace.data.data_source.remote_data_source.SatelliteAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideMockInterceptor(): MockInterceptor = MockInterceptor()

    @Singleton
    @Provides
    fun provideDefaultApiClient(
        mockInterceptor: MockInterceptor
    ): OkHttpClient {

        val builder = OkHttpClient.Builder()
            .readTimeout(AppUtil.timeoutInterval.toLong(), TimeUnit.SECONDS)
            .connectTimeout(AppUtil.timeoutInterval.toLong(), TimeUnit.SECONDS)
            .addInterceptor(mockInterceptor)

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideAnalyticsService(
        okHttpClient: OkHttpClient
    ): SatelliteAPI {

        return Retrofit.Builder()
            .baseUrl("https://google.com.tr/?gfe_rd=cr&dcr=0&ei=cOIsWt7tCI6BX56BnLgD")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(SatelliteAPI::class.java)
    }

}
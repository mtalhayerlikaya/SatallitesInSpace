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
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {


    @Singleton
    @Provides
    fun gsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideMockInterceptor(): MockInterceptor = MockInterceptor()

/*    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)*/

    @Singleton
    @Provides
    fun provideDefaultApiClient(
        // httpLoggingInterceptor: HttpLoggingInterceptor,
        mockInterceptor: MockInterceptor
    ): OkHttpClient {

        val builder = OkHttpClient.Builder()
            // .addInterceptor(httpLoggingInterceptor)
            .readTimeout(AppUtil.timeoutInterval.toLong(), TimeUnit.SECONDS)
            .connectTimeout(AppUtil.timeoutInterval.toLong(), TimeUnit.SECONDS)
            .addInterceptor(mockInterceptor)

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideAnalyticsService(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): SatelliteAPI {

        return Retrofit.Builder()
            .baseUrl("https://")
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
            .create(SatelliteAPI::class.java)
    }

}

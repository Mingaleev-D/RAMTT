package com.example.ramtt.di

import com.example.ramtt.common.Constants.BASE_URL
import com.example.ramtt.data.remote.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

   @Provides
   fun provideBaseUrl(): String {
      return BASE_URL
   }

   @Provides
   @Singleton
   fun provideLoggingInterceptor(): HttpLoggingInterceptor {
      return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
   }

   @Provides
   @Singleton
   fun provideOkHttpClient(logger: HttpLoggingInterceptor): OkHttpClient {
      val okHttpClient = OkHttpClient.Builder()
      okHttpClient.addInterceptor(logger)
      okHttpClient.callTimeout(60, TimeUnit.SECONDS)
      okHttpClient.connectTimeout(60, TimeUnit.SECONDS)
      okHttpClient.writeTimeout(60, TimeUnit.SECONDS)
      okHttpClient.readTimeout(60, TimeUnit.SECONDS)
      val okHttp = okHttpClient.build()
      return okHttp
   }

   @Provides
   @Singleton
   fun provideConvectorFactory(): GsonConverterFactory {
      return GsonConverterFactory.create()
   }

   @Provides
   @Singleton
   fun provideRetrofit(
      baseUrl: String,
      gsonFactory: GsonConverterFactory,
      okHttpClient: OkHttpClient
   ): Retrofit {
      return Retrofit.Builder()
         .baseUrl(baseUrl)
         .addConverterFactory(gsonFactory)
         .client(okHttpClient)
         .build()
   }

   @Provides
   @Singleton
   fun provideProductsService(retrofit: Retrofit): ApiService {
      return retrofit.create(ApiService::class.java)
   }
}
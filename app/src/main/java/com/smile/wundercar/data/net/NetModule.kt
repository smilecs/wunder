package com.smile.wundercar.data.net

import com.smile.wundercar.repo.api.VehicleService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetModule {
    private fun provideOkhttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
        client.addInterceptor(interceptor)
        client.addInterceptor { chain ->
            val request = chain.request().newBuilder()
            request.addHeader("Content-Type", "application/json")
            request.addHeader("Accept", "application/json")
            chain.proceed(request.build())
        }
        return client.build()
    }

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(Urls.BASE_URL)
                .client(provideOkhttpClient())
                .build()
    }

    fun getVehicleService(): VehicleService {
        return provideRetrofit().create(VehicleService::class.java)
    }
}
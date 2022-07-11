package com.juandomingo.mypharmamemorymvc.dependencyinjection

import com.juandomingo.mypharmamemorymvc.data.network.PharmaApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        //  El objeto RetrofitHelper.kt, que hacía lo mismo, se elimina:
        return Retrofit.Builder()
            .baseUrl("https://drawsomething-59328-default-rtdb.europe-west1.firebasedatabase.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    


    @Provides
    @Singleton
    fun providePharmaApiClient(retrofit: Retrofit): PharmaApiClient {
        return retrofit.create(PharmaApiClient::class.java)
    }
}
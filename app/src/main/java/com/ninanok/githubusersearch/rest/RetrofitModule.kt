package com.ninanok.githubusersearch.rest

import com.ninanok.githubusersearch.BuildConfig
import com.ninanok.githubusersearch.Consts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Consts.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authTokenInterceptor())
            .build()
    }

    private fun authTokenInterceptor(): (Interceptor.Chain) -> Response {
        val authToken = BuildConfig.GITHUB_TOKEN
        return { chain ->
            try {
                val requestBuilder = chain.request().newBuilder()
                requestBuilder.header("Accept", "application/vnd.github+json")
                requestBuilder.header("authorization", "Bearer $authToken")
                val request = requestBuilder.build()
                chain.proceed(request)
            } catch (e: Exception) {
                val defaultMessage = "network error"
                Response.Builder()
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_1)
                    .code(999)
                    .message(e.message ?: run { defaultMessage })
                    .body(ResponseBody.create(null, e.message ?: run { defaultMessage }))
                    .build()
            }
        }
    }
}
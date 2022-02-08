package com.julio.coppel.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.julio.coppel.BuildConfig
import com.julio.coppel.framework.data.remote.ApiService
import com.julio.coppel.utils.Constants
import com.julio.coppel.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Módulo que provee las instancias únicas para el acceso a Retrofit.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Timeouts.
     */
    private const val READ_TIMEOUT = 30
    private const val WRITE_TIMEOUT = 30
    private const val CONNECTION_TIMEOUT = 10
    private const val CACHE_SIZE_BYTES = 10 * 1024 * 1024L

    /**
     * Provee acceso a la instancia de la aplicación.
     *
     * @param app Contexto.
     *
     * @return Aplicación.
     */
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): App {
        return app as App
    }

    /**
     * Provee la instancia de retrofit.
     *
     * @param client Cliente.
     *
     * @return Instancia única de retrofit.
     */
    @Provides
    @Singleton
    fun retrofitProvider(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    /**
     * Provee una instancia del cliente Http.
     *
     * @param headerInterceptor Interceptor para los headers.
     * @param cache Cache.
     *
     * @return Instancia del cliente.
     */
    @Provides
    @Singleton
    fun okHttpClientProvider(
        headerInterceptor: Interceptor,
        cache: Cache
    ): OkHttpClient {

        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.cache(cache)
        okHttpClientBuilder.addInterceptor(headerInterceptor)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(logging)
        }

        return okHttpClientBuilder.build()
    }

    /**
     * Provee una instancia del interceptor para los header.
     *
     * @return Instancia única.
     */
    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor {
            val requestBuilder = it.request().newBuilder()
            requestBuilder.addHeader("Authorization", BuildConfig.ApiKey)
            it.proceed(requestBuilder.build())
        }
    }

    /**
     * Método que provee una instancia para el cache.
     *
     * @param context Contexto.
     *
     * @return Cache.
     */
    @Provides
    @Singleton
    internal fun cacheProvider(context: Context): Cache {
        val httpCacheDirectory = File(context.cacheDir.absolutePath, "HttpCache")
        return Cache(httpCacheDirectory, CACHE_SIZE_BYTES)
    }


    /**
     * Provider para el contexto de la aplicación.
     *
     * @param application Aplicación.
     *
     * @return Contexto de la aplicación enviada.
     */
    @Provides
    @Singleton
    fun contextProvider(application: App): Context {
        return application.applicationContext
    }

    /**
     * Provider para el API.
     *
     * @param retrofit Instancia de retrofit.
     *
     * @return Instancia única del service.
     */
    @Provides
    @Singleton
    fun apiProvider(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
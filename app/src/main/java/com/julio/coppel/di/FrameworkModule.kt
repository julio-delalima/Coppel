package com.julio.coppel.di

import com.julio.coppel.data.source.IImagesRemoteSource
import com.julio.coppel.framework.data.remote.ApiService
import com.julio.coppel.framework.source.ImagesRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Módulo para proveer el acceso a las fuentes de datos locales y remotas.
 */
@Module
@InstallIn(SingletonComponent::class)
object FrameworkModule {

    /**
     * Provee y genera una única instancia de la fuente de datos remota de las imágenes.
     *
     * @param apiService Api.
     * @return Instancia de la fuente de datos.
     */
    @Provides
    @Singleton
    fun galleryRemoteDataSourceProvider(apiService: ApiService): IImagesRemoteSource {
        return ImagesRemoteSource(apiService)
    }
}
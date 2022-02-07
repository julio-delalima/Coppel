package com.julio.coppel.di

import com.julio.coppel.data.repository.ImagesRepository
import com.julio.coppel.data.source.IImagesRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Módulo para proveer los repositorios necesarios.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /**
     * Proporciona un acceso al repositorio de imágenes.
     *
     * @param remote Fuente de datos remota de las imágenes.
     * @return Fuente de datos.
     */
    @Provides
    @Singleton
    fun imagesRepositoryProvider(remote: IImagesRemoteSource): ImagesRepository {
        return ImagesRepository(remote)
    }

}
package com.julio.coppel.di

import com.julio.coppel.data.repository.ImagesRepository
import com.julio.coppel.domain.usecase.GetImages
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Módulo para proveer instancias únicas de los casos de uso.
 */
@Module
@InstallIn(SingletonComponent::class)
object UsesCasesModule {

    /**
     * Provee el caso de uso para consultar las imágenes.
     *
     * @param repository Repositorio de imágenes.
     *
     * @return Caso de uso.
     */
    @Provides
    @Singleton
    fun getImagesProvider(repository: ImagesRepository): GetImages {
        return GetImages(repository)
    }
}
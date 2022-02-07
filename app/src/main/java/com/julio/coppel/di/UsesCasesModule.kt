package com.julio.coppel.di

import com.julio.coppel.data.repository.ImagesRepository
import com.julio.coppel.domain.usecase.GetImage
import com.julio.coppel.domain.usecase.GetImages
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UsesCasesModule {

    @Provides
    @Singleton
    fun getImagesProvider(repository: ImagesRepository): GetImages {
        return GetImages(repository)
    }

    @Provides
    @Singleton
    fun getImageProvider(repository: ImagesRepository): GetImage {
        return GetImage(repository)
    }
}
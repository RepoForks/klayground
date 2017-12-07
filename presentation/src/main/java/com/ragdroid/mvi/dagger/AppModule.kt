package com.ragdroid.mvi.dagger

import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.ragdroid.data.entity.AppConfig
import com.ragdroid.mvi.BuildConfig
import com.ragdroid.mvi.MarvelApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by garimajain on 18/11/17.
 */
@Module
class AppModule {

    @Provides
    fun provideMarvelConfig(): AppConfig {
        return AppConfig(BuildConfig.BASE_URL,
                BuildConfig.PUBLIC_KEY,
                BuildConfig.PRIVATE_KEY)
    }


    @Singleton
    @Provides
    fun provideGlide(application: MarvelApplication): RequestManager {
        return Glide.with(application)
    }

}
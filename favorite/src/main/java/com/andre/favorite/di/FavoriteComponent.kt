package com.andre.favorite.di

import com.andre.cinamate.di.AppModule
import com.andre.cinamate.presentation.favorite.FavoriteFragment
import dagger.Component

@Component(dependencies = [AppModule::class])
interface FavoriteComponent {
    @Component.Factory
    interface Factory {
        // Takes an instance of AppComponent when creating
        // an instance of LoginComponent
        fun create(appComponent: AppModule): FavoriteComponent
    }

    fun inject(activity: FavoriteFragment)
}

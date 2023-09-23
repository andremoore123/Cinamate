package com.andre.favorite.di

import android.content.Context
import com.andre.cinamate.di.FavoriteModuleDependencies
import com.andre.favorite.presentation.FavoriteFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent{
    fun inject(fragment: FavoriteFragment)
    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(loginModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }
}
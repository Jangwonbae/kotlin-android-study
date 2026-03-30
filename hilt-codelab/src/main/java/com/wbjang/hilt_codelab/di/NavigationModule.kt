package com.wbjang.hilt_codelab.di

import com.wbjang.hilt_codelab.navigator.AppNavigator
import com.wbjang.hilt_codelab.navigator.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class NavigationModule {

    @Binds
    abstract fun bindNavigator(impl: AppNavigatorImpl): AppNavigator
}
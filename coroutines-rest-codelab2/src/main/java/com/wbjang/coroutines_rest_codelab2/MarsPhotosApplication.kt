package com.wbjang.coroutines_rest_codelab2

import android.app.Application
import com.wbjang.coroutines_rest_codelab2.data.AppContainer
import com.wbjang.coroutines_rest_codelab2.data.DefaultAppContainer

class MarsPhotosApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
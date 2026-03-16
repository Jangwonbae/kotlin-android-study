package com.wbjang.coroutines_rest_coil_codelab_bookshelf

import android.app.Application
import com.wbjang.coroutines_rest_coil_codelab_bookshelf.data.AppContainer
import com.wbjang.coroutines_rest_coil_codelab_bookshelf.data.DefaultAppContainer

class BookshelfApplication : Application() {
    lateinit var  appContainer : AppContainer
    override fun onCreate() {
        super.onCreate()
        appContainer = DefaultAppContainer()
    }
}
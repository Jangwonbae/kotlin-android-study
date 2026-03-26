package com.wbjang.work_manager_codelab

import android.app.Application
import com.wbjang.work_manager_codelab.data.AppContainer
import com.wbjang.work_manager_codelab.data.DefaultAppContainer

class BluromaticApplication : Application()  {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}
package com.example.alibabapush

import android.app.Application
import android.content.Context
import android.util.Log
import com.mpaas.mps.adapter.api.MPPush

class MyApplication: Application() {

    var application = this

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Log.e("MyApplication", "attachBaseContext:: On")


        application = this
        MPPush.setup(this)

    }

    override fun onCreate() {
        super.onCreate()
        MPPush.setBadgeActivityClassName(this, LauncherActivity::class.simpleName)
        MPPush.setBadgeAutoClearEnabled(this, true)
        MPPush.clearBadges(this)
    }

}
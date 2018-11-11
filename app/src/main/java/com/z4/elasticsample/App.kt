package com.z4.elasticsample

import android.app.Application
import uk.co.chrisjenx.calligraphy.CalligraphyConfig



class App: Application() {

    override fun onCreate() {
        super.onCreate()

        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }
}
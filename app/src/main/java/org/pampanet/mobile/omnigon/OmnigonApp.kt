package org.pampanet.mobile.omnigon

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class OmnigonApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}
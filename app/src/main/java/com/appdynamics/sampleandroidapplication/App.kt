package com.appdynamics.sampleandroidapplication

import android.app.Application
import com.appdynamics.eumagent.runtime.AgentConfiguration
import com.appdynamics.eumagent.runtime.Instrumentation

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // Instrumentation of AppD
        Instrumentation.start(
            AgentConfiguration.builder()
                .withAppKey("APPKEY")
                .withContext(applicationContext)
                .withCollectorURL("https://col.eum-appdynamics.com")
                .withScreenshotURL("https://col.eum-appdynamics.com")
                .withLoggingLevel(Instrumentation.LOGGING_LEVEL_VERBOSE)
                .build())
    }
}
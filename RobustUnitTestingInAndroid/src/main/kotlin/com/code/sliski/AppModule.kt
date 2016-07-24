package com.code.sliski

import android.content.Context
import com.code.sliski.preference.PreferencesManager
import com.code.sliski.preference.PreferencesManagerImpl
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: App) {

    @Provides
    fun preferencesManager(context: Context): PreferencesManager =
            PreferencesManagerImpl(context.getSharedPreferences(context.getString(R.string.preferences),
                                                                Context.MODE_PRIVATE))

    @Provides
    fun applicationContext(): Context = application
}
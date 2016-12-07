package com.code.sliski.mainscreen.di

import android.content.Context
import com.code.sliski.R.string.*
import com.code.sliski.UserRole.*
import com.code.sliski.mainscreen.ui.MainActivityPresenter
import com.code.sliski.preference.PreferencesManager
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides @MainScope
    fun mainActivityPresenter(preferencesManager: PreferencesManager, context: Context): MainActivityPresenter {
        val userRoleDictionary = mapOf(Pair(HERO, context.getString(hero_hello)),
                                       Pair(GRUNT, context.getString(grunt_hello)),
                                       Pair(PEON, context.getString(peon_hello)))
        return MainActivityPresenter(preferencesManager.getUserId(),
                                     HERO,
                                     userRoleDictionary)
    }
}


package ru.vtb.storage

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesProvider @Inject constructor(
    @ApplicationContext val context: Context
) {

    private companion object {
        const val TOKEN = "TOKEN"
        const val INITIAL = "INITIAL"
    }

    private val preferences by lazy {
        context.getSharedPreferences("USER_PREFERENCES", Context.MODE_PRIVATE)
    }

    suspend fun saveUserToken(token: String) = withContext(Dispatchers.IO) {
        preferences.edit().putString(TOKEN, token).apply()
    }

    suspend fun getUserToken(): String? = withContext(Dispatchers.IO) {
        preferences.getString(TOKEN, null)
    }

    suspend fun savePassedInitialIntroduction() = withContext(Dispatchers.IO) {
        preferences.edit().putBoolean(INITIAL, true).apply()
    }

    suspend fun getPassedInitialIntroduction(): Boolean = withContext(Dispatchers.IO) {
        preferences.getBoolean(INITIAL, false)
    }
}
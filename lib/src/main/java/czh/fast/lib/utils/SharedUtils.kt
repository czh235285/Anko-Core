package czh.fast.lib.utils

import android.content.Context
import android.preference.PreferenceManager

object SharedUtils {
    fun getPrefString(context: Context, key: String,
                      defaultValue: String): String {
        val settings = PreferenceManager
                .getDefaultSharedPreferences(context)
        return settings.getString(key, defaultValue)
    }

    fun setPrefString(context: Context, key: String,
                      value: String) {
        val settings = PreferenceManager
                .getDefaultSharedPreferences(context)
        settings.edit().putString(key, value).commit()
    }

    fun getPrefBoolean(context: Context, key: String,
                       defaultValue: Boolean): Boolean {
        val settings = PreferenceManager
                .getDefaultSharedPreferences(context)
        return settings.getBoolean(key, defaultValue)
    }

    fun hasKey(context: Context, key: String): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(context).contains(
                key)
    }

    fun setPrefBoolean(context: Context, key: String,
                       value: Boolean) {
        val settings = PreferenceManager
                .getDefaultSharedPreferences(context)
        settings.edit().putBoolean(key, value).commit()
    }

    fun setPrefInt(context: Context, key: String,
                   value: Int) {
        val settings = PreferenceManager
                .getDefaultSharedPreferences(context)
        settings.edit().putInt(key, value).commit()
    }

    fun getPrefInt(context: Context, key: String,
                   defaultValue: Int): Int {
        val settings = PreferenceManager
                .getDefaultSharedPreferences(context)
        return settings.getInt(key, defaultValue)
    }

    fun setPrefFloat(context: Context, key: String,
                     value: Float) {
        val settings = PreferenceManager
                .getDefaultSharedPreferences(context)
        settings.edit().putFloat(key, value).commit()
    }

    fun getPrefFloat(context: Context, key: String,
                     defaultValue: Float): Float {
        val settings = PreferenceManager
                .getDefaultSharedPreferences(context)
        return settings.getFloat(key, defaultValue)
    }

    fun setSettingLong(context: Context, key: String,
                       value: Long) {
        val settings = PreferenceManager
                .getDefaultSharedPreferences(context)
        settings.edit().putLong(key, value).commit()
    }

    fun getPrefLong(context: Context, key: String,
                    defaultValue: Long): Long {
        val settings = PreferenceManager
                .getDefaultSharedPreferences(context)
        return settings.getLong(key, defaultValue)
    }

    fun clearPreference(context: Context) {
        val settings = PreferenceManager
                .getDefaultSharedPreferences(context)
        val editor = settings.edit()
        editor.clear()
        editor.commit()
    }
}

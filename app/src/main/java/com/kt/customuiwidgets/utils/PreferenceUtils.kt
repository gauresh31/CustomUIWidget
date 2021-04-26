package com.kt.customuiwidgets.utils

import android.content.Context
import android.preference.PreferenceManager
import java.util.*

object PreferenceUtils {
    const val PREFS_NAME = "CustomUIPrefs"
    private const val BLANK = ""

    /**
     * Removes all SharedPreference
     *
     * @param context
     */
    fun removeAll(context: Context) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit().clear().apply()
    }

    /**
     * @param context
     * @param Key     to remove
     */
    fun remove(context: Context?, Key: String?) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        editor.remove(Key)
        editor.apply()
    }

    /**
     * Gets string data saved in SharedPreference
     *
     * @param context
     * @param key
     * @return String data. Default return value is ""
     */
    fun getStringPreference(context: Context, key: String?): String? {
        var value = ""
        val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        try {
            value = preferences.getString(key, BLANK).toString()
        } catch (e: Exception) {
            e.printStackTrace()
            return value
        }
        return value
    }

    /**
     * Set String data in SharedPreference
     *
     * @param context
     * @param key
     * @param value
     */
    fun setStringPreference(context: Context, key: String?, value: String?) {
        val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    /**
     * Get boolean data saved in SharedPreference.
     *
     * @param context
     * @param key
     * @return boolean data. Default return value is "false".
     */
    fun getBooleanPreference(context: Context, key: String?): Boolean {
        var value = false
        val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        value = try {
            preferences.getBoolean(key, value)
        } catch (e: Exception) {
            e.printStackTrace()
            return value
        }
        return value
    }

    /**
     * Set boolean data in SharedPreference
     *
     * @param context
     * @param key
     * @param value
     */
    fun setBooleanPreference(context: Context, key: String?, value: Boolean) {
        val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    /**
     * Get int data from SharedPreference
     *
     * @param context
     * @param key
     * @return int data. Default return value is "0"
     */
    fun getIntPreference(context: Context, key: String?): Int {
        var value = 0
        val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        value = preferences.getInt(key, 0)
        return value
    }

    /**
     * Get int data from SharedPreference
     *
     * @param context
     * @param key
     * @param defValue
     * @return int data. Default return value is defValue
     */
    fun getIntPreference(context: Context, key: String?, defValue: Int): Int {
        var value = defValue
        val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        value = preferences.getInt(key, defValue)
        return value
    }

    /**
     * Set int data in SharedPreference
     *
     * @param context
     * @param key
     * @param value
     */
    fun setIntPreference(context: Context, key: String?, value: Int) {
        val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    /**
     * Set long data in SharedPreference
     *
     * @param context
     * @param key
     * @param value
     */
    fun setLongPreference(context: Context, key: String?, value: Long) {
        val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    /**
     * Get long data from SharedPreference
     *
     * @param context
     * @param key
     * @return long data. Default return type is "0"
     */
    fun getLongPreference(context: Context, key: String?): Long {
        var value: Long = 0
        val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        value = preferences.getLong(key, 0)
        return value
    }

    /**
     * Set String data in SharedPreference
     *
     * @param context
     * @param key
     * @param value
     */
    fun setArrayStringPreference(
        context: Context,
        key: String,
        value: ArrayList<String?>
    ): Boolean {
        val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putInt(key + "Size", value.size)
        for (i in value.indices) {
            editor.remove(key + i)
            editor.putString(key + i, value[i])
        }
        return editor.commit()
    }

    /**
     * Get String data in SharedPreference
     *
     * @param context
     * @param key
     */
    fun getArrayStringPreference(context: Context, key: String): ArrayList<String?> {
        val stringArray = ArrayList<String?>()
        val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        stringArray.clear()
        val size = preferences.getInt(key + "Size", 0)
        for (i in 0 until size) {
            stringArray.add(preferences.getString(key + i, null))
        }
        return stringArray
    }
}
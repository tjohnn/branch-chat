package com.example.branchchat.data.local

import android.content.Context


class AppPreferences(context: Context) {

    private val mSharedPreferences = context.getSharedPreferences("BranchChatApp", Context.MODE_PRIVATE)
    private val mSharedPreferencesEditor = mSharedPreferences.edit()

    var authToken: String?
        get() {
            return mSharedPreferences.getString(PREF_KEY_AUTH_TOKEN, null)
        }
        set(value) {
            mSharedPreferencesEditor.putString(PREF_KEY_AUTH_TOKEN, value).commit()
        }


    companion object {
        const val PREF_KEY_AUTH_TOKEN = "PREF_KEY_AUTH_TOKEN"
    }
}
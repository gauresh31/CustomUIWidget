package com.kt.customuiwidgets.utils

import android.content.Context
import android.content.Intent
import android.util.Patterns
import android.view.Gravity
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kt.customuiwidgets.R
import com.kt.customuiwidgets.activities.LoginActivity


object Utils {

    fun checkMinLength(editText: EditText?, minLen: Int): Boolean {
        val textLen = editText?.length()
        if (textLen != null && textLen < minLen) {
            return false
        }
        return true
    }

    @JvmStatic
    fun logoutUser(context: AppCompatActivity) {
        if(PreferenceUtils.getBooleanPreference(
                context,
                context.getString(R.string.str_is_logged_in)
            )
                || PreferenceUtils.getBooleanPreference(
                context,
                context.getString(R.string.str_is_registered)
            )) {
            PreferenceUtils.setBooleanPreference(
                context,
                context.getString(R.string.str_is_logged_in), false
            )
            val myIntent = Intent(context, LoginActivity::class.java)
            myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(myIntent)
            context.finish()
        }
    }

    /**
     * Method to show long Toast Message.
     *
     * @param ctx
     * @param message
     */
    fun showLongToast(ctx: Context?, message: String?) {
        val toast = Toast.makeText(ctx, message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    fun isValidEmail(context: Context, text: String) : Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(text).matches()
    }
}
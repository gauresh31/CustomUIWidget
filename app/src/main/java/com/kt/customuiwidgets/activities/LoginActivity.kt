package com.kt.customuiwidgets.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.kt.customuiwidgets.R
import com.kt.customuiwidgets.utils.PreferenceUtils
import com.kt.customuiwidgets.utils.Utils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    private lateinit var context: Context

    fun LoginActivity(context: Context) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        setContentView(R.layout.activity_login)

        init()
        setDefaults()
    }

    private fun init() {
        context = this
    }

    private fun setDefaults() {
        title = "Login"

        if (PreferenceUtils.getBooleanPreference(
                context,
                context.getString(R.string.str_is_logged_in)
            )
        ) {
            PreferenceUtils.setLongPreference(
                context, getString(R.string.str_last_time),
                0
            )
            val myIntent = Intent(context, MainActivity::class.java)
            startActivity(myIntent)
            finish()
        }

        btn_login.setOnClickListener {
            when {
                editTextLoginEmailAddress.text.isEmpty() -> {
                    editTextLoginEmailAddress.error = "Enter Email"
                    Utils.showLongToast(context, "Enter Email")
                }
                editTextLoginPassword.text.isEmpty() -> {
                    editTextLoginPassword.error = "Enter Password"
                    Utils.showLongToast(context, "Enter Password")
                }
                editTextLoginPassword.text.isNotEmpty() && !Utils.checkMinLength(
                    editTextLoginPassword,
                    10
                ) -> {
                    editTextLoginPassword.error = "Password minimum 10 characters"
                    Utils.showLongToast(context, "Password minimum 10 characters")
                }
                else -> {
                    val username = PreferenceUtils.getStringPreference(
                        context,
                        context.getString(R.string.str_user_name)
                    )
                    val password = PreferenceUtils.getStringPreference(
                        context,
                        context.getString(R.string.str_pass)
                    )
                    if (editTextLoginEmailAddress.text.toString() == username
                        && editTextLoginPassword.text.toString() == password) {
                        PreferenceUtils.setLongPreference(
                            context, getString(R.string.str_last_time),
                            0
                        )
                        PreferenceUtils.setBooleanPreference(
                            context,
                            context.getString(R.string.str_is_logged_in), true
                        )
                        val myIntent = Intent(context, MainActivity::class.java)
                        startActivity(myIntent)
                        finish()
                    } else {
                        Utils.showLongToast(context, "Username or Password doesn't match")
                    }
                }
            }
        }
    }

    fun validate(userName: String, password: String): String? {
        return if (userName == "user" && password == "user") "Login was successful" else "Invalid login!"
    }
}
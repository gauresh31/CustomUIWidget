package com.kt.customuiwidgets.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.kt.customuiwidgets.R
import com.kt.customuiwidgets.utils.PreferenceUtils
import com.kt.customuiwidgets.utils.Utils
import com.kt.customuiwidgets.utils.Utils.isValidEmail
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : BaseActivity() {
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        setContentView(R.layout.activity_sign_up)

        init()
        setDefaults()
        setListeners()
    }

    private fun init() {
        context = this
        title = "Register"
    }

    private fun setDefaults() {
        if (PreferenceUtils.getBooleanPreference(
                context,
                context.getString(R.string.str_is_logged_in))
                    || PreferenceUtils.getBooleanPreference(
                context,
                context.getString(R.string.str_is_registered))) {
            val myIntent = Intent(context, LoginActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }

    private fun setListeners() {
        btn_sign_up_submit.setOnClickListener {

            when {
                editSignUpPersonName.text.isEmpty() -> {
                    editSignUpPersonName.error = "Enter Name"
                    Utils.showLongToast(context,"Enter Name")
                }
                editTextSignUpEmailAddress.text.isEmpty() -> {
                    editTextSignUpEmailAddress.error = "Enter Email"
                    Utils.showLongToast(context,"Enter Email")
                }
                editTextSignUpEmailAddress.text.isNotEmpty() &&
                                !isValidEmail(context, editTextSignUpEmailAddress.text.toString())-> {
                    editTextSignUpEmailAddress.error = "Enter Valid Email"
                    Utils.showLongToast(context,"Enter Valid Email")
                }
                editTextSignUpPassword.text.isEmpty() -> {
                    editTextSignUpPassword.error = "Enter Password"
                    Utils.showLongToast(context,"Enter Password")
                }
                editTextSignUpPassword.text.isNotEmpty() && !Utils.checkMinLength(
                    editTextSignUpPassword,
                    10
                ) -> {
                    editTextSignUpPassword.error = "Password minimum 10 characters"
                    Utils.showLongToast(context,"Password minimum 10 characters")
                }
                else -> {
                    PreferenceUtils.setStringPreference(
                        context,
                        context.getString(R.string.str_fullname),
                        editSignUpPersonName.text.toString()
                    )
                    PreferenceUtils.setStringPreference(
                        context,
                        context.getString(R.string.str_user_name),
                        editTextSignUpEmailAddress.text.toString()
                    )
                    PreferenceUtils.setStringPreference(
                        context,
                        context.getString(R.string.str_pass),
                        editTextSignUpPassword.text.toString()
                    )
                    PreferenceUtils.setBooleanPreference(
                        context,
                        context.getString(R.string.str_is_registered),
                        true
                    )
                    Utils.showLongToast(context,"Registration Successful....Please Login")
                    val myIntent = Intent(context, LoginActivity::class.java)
                    startActivity(myIntent)
                    finish()
                }
            }

        }

    }
}
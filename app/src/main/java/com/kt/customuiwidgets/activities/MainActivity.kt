package com.kt.customuiwidgets.activities

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kt.customuiwidgets.R
import com.kt.customuiwidgets.utils.PreferenceUtils
import com.kt.customuiwidgets.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        setContentView(R.layout.activity_main)

        setDefaults()
    }

    private fun setDefaults() {
        context = this
        val fullname = PreferenceUtils.getStringPreference(
            context,
            context.getString(R.string.str_fullname)
        )
        tv_name.text = "Welcome, $fullname"

        btn_logout.setOnClickListener {
            Utils.logoutUser(context as AppCompatActivity)
        }
    }


    override fun onStop() {
        super.onStop()
        PreferenceUtils.setLongPreference(
            context, getString(R.string.str_last_time),
            System.currentTimeMillis()
        )
    }

    override fun onResume() {
        super.onResume()
        if (PreferenceUtils.getLongPreference(context, getString(R.string.str_last_time)) > 0) {
            if (System.currentTimeMillis() >=
                PreferenceUtils.getLongPreference(
                    context,
                    getString(R.string.str_last_time)
                ) + 10000
            ) {
                Utils.logoutUser(context as AppCompatActivity)
            }
        }
    }
}
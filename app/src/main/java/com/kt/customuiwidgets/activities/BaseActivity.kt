package com.kt.customuiwidgets.activities

import android.app.ActivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kt.customuiwidgets.utils.Utils.logoutUser

open class BaseActivity : AppCompatActivity() {

    val DISCONNECT_TIMEOUT: Long = 30000
    private var isInBack: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myProcess = ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(myProcess);
        isInBack =
            myProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
        if (isInBack) {
            Log.d("BaseActivity", "Your application is in Background state");
        } else {
            Log.d("BaseActivity", "Your application is in Foreground state");
        }
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        resetDisconnectTimer()
    }

    val disconnectHandler = Handler { msg: Message? -> true }

    val disconnectCallback = Runnable {
        logoutUser(
            getAppContext()
        )
    }

    fun resetDisconnectTimer() {
        disconnectHandler.removeCallbacks(disconnectCallback)
        disconnectHandler.postDelayed(disconnectCallback, DISCONNECT_TIMEOUT)
    }

    fun stopDisconnectTimer() {
        disconnectHandler.removeCallbacks(disconnectCallback)
    }

    override fun onResume() {
        super.onResume()
        resetDisconnectTimer()
    }

    override fun onStop() {
        super.onStop()
        stopDisconnectTimer()
    }

    private fun getAppContext(): AppCompatActivity {
        return (this as AppCompatActivity?)!!
    }
}

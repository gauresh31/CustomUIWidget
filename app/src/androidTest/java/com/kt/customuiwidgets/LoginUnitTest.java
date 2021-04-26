package com.kt.customuiwidgets;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.kt.customuiwidgets.activities.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class LoginUnitTest {

    private static final String SUCCESS_STRING = "Login was successful";

//    @TargetContext
//    Context context;

//    @Rule
//    public ActivityTestRule<LoginActivity> mActivityRule =
//            new ActivityTestRule(LoginActivity.class);

    @Test
    public void readStringFromContext_LocalizedString() {

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                LoginActivity myObjectUnderTest = new LoginActivity();

                String result = myObjectUnderTest.validate("user","user");

                assertThat(result, is(SUCCESS_STRING));
            }
        });

    }

}
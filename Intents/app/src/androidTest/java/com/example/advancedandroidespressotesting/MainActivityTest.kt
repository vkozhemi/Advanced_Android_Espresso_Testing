package com.example.advancedandroidespressotesting

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.ComponentNameMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule @JvmField

    var activityRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun punnyLaunchActivity() {
        onView(withId(R.id.button_punny))
            .perform(click())

        onView(withId(R.id.theme))
            .check(matches(withText(R.string.theme_punny)))
    }

    @Test
    fun punnyIntended() {
        onView(withId(R.id.button_punny))
            .perform(click())

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val theme = context.getString(R.string.theme_punny)
        Intents.intended(hasExtra(IdeasActivity.KEY_THEME, theme))
        Intents.intended(IntentMatchers.hasComponent(
            ComponentNameMatchers.hasClassName(
                "com.example.advancedandroidespressotesting.IdeasActivity")
        ))
    }

    @Test
    fun punnyIntending() {
        val name = "Cataline Portman"

        val intent = Intent()
        intent.putExtra(IdeasActivity.KEY_NAME, name)
        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, intent)

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val theme = context.getString(R.string.theme_punny)
        Intents.intending(hasExtra(IdeasActivity.KEY_THEME, theme)).respondWith(result)

        onView(withId(R.id.button_punny))
            .perform(click())

        onView(withId(R.id.name))
            .check(matches(withText(name)))
    }
}
package com.example.advancedandroidespressotesting

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IdeasActivityTest {
    @Rule @JvmField
    var activityRule = ActivityTestRule(IdeasActivity::class.java, true, false)


    @Test
    fun noTheme() {
        activityRule.launchActivity(null)
        onView(withId(R.id.theme))
            .check(matches(withText(R.string.missing_theme)))
    }

    @Test
    fun punny() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val theme = context.getString(R.string.theme_punny)
        val intent = Intent()
        intent.putExtra(IdeasActivity.KEY_THEME, theme)
        activityRule.launchActivity(intent)

        onView(withId(R.id.theme))
            .check(matches(withText(theme)))
    }

    @Test
    fun unknown() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val theme = "silly"

        val intent = Intent()
        intent.putExtra(IdeasActivity.KEY_THEME, theme)
        activityRule.launchActivity(intent)

        val message = context.getString(R.string.unknown_theme, theme)
        onView(withId(R.id.theme))
            .check(matches(withText(message)))
    }
}
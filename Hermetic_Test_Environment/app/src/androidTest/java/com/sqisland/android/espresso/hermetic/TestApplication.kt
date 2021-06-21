package com.sqisland.android.espresso.hermetic

import org.junit.Test

import org.junit.Assert.*

class TestApplication : GreetingApplication() {
    private lateinit var clock: Clock

    fun setClock(clock: Clock) {
        this.clock = clock
    }

    override fun provideClock() : Clock = clock

}
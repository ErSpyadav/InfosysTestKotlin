package demo.expresso.infosystestkotlin.view

import androidx.test.espresso.Espresso.onView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import demo.expresso.infosystestkotlin.R
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*

class MainActivityTest {

    @get:Rule var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun uiTest(){
        onView(withId(R.id.recycleView)).check(matches(isDisplayed()))
        onView(withId(R.id.pullToRefresh)).check(matches(isDisplayed()))

    }

}
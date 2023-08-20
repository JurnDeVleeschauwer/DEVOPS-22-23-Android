package com.hogent.devOps_Android.screens.profiel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.hogent.devOps_Android.app.MainActivity
import com.hogent.devOps_Android.R
import com.hogent.devOps_Android.ui.klant.CustomerProfileFragment
import com.hogent.devOps_Android.ui.login.CredentialsManager
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class profielViewTest {

    @get:Rule
    var activityScenarioRule = ActivityTestRule(
        MainActivity::class.java
    )

    /*@get:Rule
    var mInstantTaskExecutorRule = InstantTaskExecutorRule()*/

    @Before
    fun intentsInit() {
        // initialize Espresso Intents capturing
        CredentialsManager.UserId = "auth0|6390964a894d42544f733938"
        Intents.init()
    }

    @After
    fun intentsTeardown() {
        // release Espresso Intents capturing
        Intents.release()
    }


    @Test
    fun testCustomerProfileFragment() {
        launchFragmentInContainer<CustomerProfileFragment>()

        onView(withId(R.id.titleKlantDetails)).check(matches(withText("Klanten details")));
    }

    fun testCustomerProfileFragmentUserVisable() {
        launchFragmentInContainer<CustomerProfileFragment>()

        onView(withId(R.id.emailadress)).check(matches(withText("jack.verhaegenn@gmail.com")));
    }


}
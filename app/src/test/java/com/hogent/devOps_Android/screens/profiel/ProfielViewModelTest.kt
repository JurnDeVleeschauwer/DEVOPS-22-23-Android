package com.hogent.devOps_Android.screens.profiel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hogent.devOps_Android.ui.klant.CustomerViewModel
import org.hamcrest.CoreMatchers.`is`
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.core.IsNot.not


@RunWith(AndroidJUnit4::class)
class profielViewModelTest {

    /*@get:Rule
    var activityScenarioRule = ActivityScenarioRule(
        MainActivity::class.java
    )*/
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    /*@Before
    fun intentsInit() {
        // initialize Espresso Intents capturing
        Intents.init()
    }

    @After
    fun intentsTeardown() {
        // release Espresso Intents capturing
        Intents.release()
    }*/

    /*@Test
    @Throws(Exception::class)
    fun shouldNotBeNull() {
        val fragment: CustomerProfileFragment = CustomerProfileFragment()
        //startFragment(fragment)
        assertNotNull(fragment)
    }*/

    @Test
    fun simple_customerViewModel_test() {
    // Given a fresh ViewModel
        val customerViewModel = CustomerViewModel(ApplicationProvider.getApplicationContext())

    // When adding a new task
        assertThat(customerViewModel.user, `is`(not(nullValue())))
    }

    /*@Test
    fun failing_customerViewModel_test() {
        // Given a fresh ViewModel
        val customerViewModel = CustomerViewModel(ApplicationProvider.getApplicationContext())

        // When adding a new task
        assertThat(customerViewModel.user, `is`(5))
    }*/

    /*@Test
    fun simple_view_test(){
    //onView(withText("Hello world!")).check(matches(isDisplayed()))
    //onView(withId(R.id.)).perform(click())



    // Use launchFragment to launch the dialog fragment in a dialog.
    val scenario = launchFragmentInContainer<CustomerProfileFragment>(initialState = Lifecycle.State.INITIALIZED)
    scenario.moveToState(Lifecycle.State.STARTED)

    scenario.onFragment { fragment ->
        fragment.view
    }
    //onView(withId(R.id.fullname))

    /*scenario.onFragment { fragment ->
        assertThat(fragment.).isNotNull()
        assertThat(fragment.requireDialog().isShowing).isTrue()
    }

    // Now use espresso to look for the fragment's text view and verify it is displayed.
    onView(withId(R.id.)).inRoot(isDialog())
        .check(ViewAssertions.matches(ViewMatchers.withText("I am a fragment")));*/
    }




    @Test
    fun failing_jokeModel_test(){

    /*with(launchFragment<CustomerProfileFragment>()) {
        onFragment { fragment ->
            assertThat(fragment.dialog).isNotNull()
            assertThat(fragment.requireDialog().isShowing).isTrue()
            fragment.dismiss()
            fragment.parentFragmentManager.executePendingTransactions()
            assertThat(fragment.dialog).isNull()
        }
    }*/

    }

    /*@Test
    fun test_liveData(){
    val VMListViewModel = VMListViewModel(app = ApplicationProvider.getApplicationContext())

    VMListViewModel.changeCurrentJoke()

    val value = VMListViewModel.currentJoke.getOrAwaitValue()

    assertThat(value, not(nullValue()))
    }*/*/
}
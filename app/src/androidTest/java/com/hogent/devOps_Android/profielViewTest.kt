package com.hogent.devOps_Android

import android.R
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.hogent.devOps_Android.app.MainActivity
import com.hogent.devOps_Android.ui.klant.CustomerProfileFragment
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class profielViewTest {

        @get:Rule
        var activityScenarioRule = ActivityTestRule(
            MainActivity::class.java
        )

        @Before
        fun intentsInit() {
            // initialize Espresso Intents capturing
            Intents.init()
        }

        @After
        fun intentsTeardown() {
            // release Espresso Intents capturing
            Intents.release()
        }

        @Test
        fun startFragment_shouldStartFragment() {
            val fragment = launchFragmentInContainer<CustomerProfileFragment>()
            //startFragment(fragment);
            fragment.moveToState(Lifecycle.State.RESUMED)

            //onView(withId(R.id.))


            fragment.onFragment {
                assert(it.view?.findViewById<TextView>(R.id.text1)?.text.toString() == "Hello From Blank")
            }
            //assertThat(fragment. .getView()).isNotNull()
            //assertThat(fragment.getActivity()).isNotNull()
            //assertThat((TextView) fragment.getView().findViewById(R.id.tacos)).isNotNull();
        }

        @Test
        fun startVisibleFragment_shouldStartFragment() {

            val customerProfileFragment = startMyFragment()


                //onView(withId(R.id.))


        }

        private fun startMyFragment(): CustomerProfileFragment {
            val activity = activityScenarioRule.getActivity() as FragmentActivity
            val transaction = activity.supportFragmentManager.beginTransaction()
            val myFragment = CustomerProfileFragment()
            transaction.add(myFragment, "myfrag")
            transaction.commit()
        return myFragment
    }

        @Test
        fun startVisibleFragment_shouldAttachFragmentToActivity() {
            activityScenarioRule.getActivity()
                .runOnUiThread(Runnable { val voiceFragment: CustomerProfileFragment = startMyFragment() })

            // Then use Espresso to test the Fragment
            //onView(withId(R.id.)).check(matches(isDisplayed()))
        }/*

        @Test
        fun startFragment_shouldStartFragmentWithSpecifiedActivityClass() {
            final LoginFragment fragment = new LoginFragment();
            startFragment(fragment, LoginActivity.class);

            assertThat(fragment.getView()).isNotNull();
            assertThat(fragment.getActivity()).isNotNull();
            assertThat((TextView) fragment.getView().findViewById(R.id.tacos)).isNotNull();
            assertThat(fragment.getActivity()).isInstanceOf(LoginActivity.class);
        }

        @Test
        fun startVisibleFragment_shouldStartFragmentWithSpecifiedActivityClass() {
            final LoginFragment fragment = new LoginFragment();
            startVisibleFragment(fragment, LoginActivity.class, 1);

            assertThat(fragment.getView()).isNotNull();
            assertThat(fragment.getActivity()).isNotNull();
            assertThat((TextView) fragment.getView().findViewById(R.id.tacos)).isNotNull();
            assertThat(fragment.getActivity()).isInstanceOf(LoginActivity.class);
        }

        @Test
        fun startVisibleFragment_shouldAttachFragmentToActivityWithSpecifiedActivityClass() {
            final LoginFragment fragment = new LoginFragment();
            startVisibleFragment(fragment, LoginActivity.class, 1);

            assertThat(fragment.getView().getWindowToken()).isNotNull();
            assertThat(fragment.getActivity()).isInstanceOf(LoginActivity.class);
        }

        public static class LoginFragment extends Fragment {
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                return inflater.inflate(R.layout.fragment_contents, container, false);
            }
        }

        fun class LoginActivity extends Activity {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                LinearLayout view = new LinearLayout(this);
                view.setId(1);

                setContentView(view);
            }
        }*/
}
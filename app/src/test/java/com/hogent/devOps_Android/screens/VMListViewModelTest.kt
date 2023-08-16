package com.hogent.devOps_Android.screens


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNot.not
import com.hogent.devOps_Android.ui.vms.overview.VMListViewModel

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VMListViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun simple_jokeModel_test(){
        val VMListViewModel = VMListViewModel(app = ApplicationProvider.getApplicationContext())

        assertThat(VMListViewModel.projects, `is`(2))
        assertThat(VMListViewModel.vms, `is`(3))
        assertThat(VMListViewModel.projectsvms, `is`(3))
    }

    @Test
    fun failing_jokeModel_test(){
        val VMListViewModel = VMListViewModel(app = ApplicationProvider.getApplicationContext())

        assertThat(VMListViewModel.projects, `is`(5))
    }

    /*@Test
    fun test_liveData(){
        val VMListViewModel = VMListViewModel(app = ApplicationProvider.getApplicationContext())

        VMListViewModel.changeCurrentJoke()

        val value = VMListViewModel.currentJoke.getOrAwaitValue()

        assertThat(value, not(nullValue()))
    }*/
}
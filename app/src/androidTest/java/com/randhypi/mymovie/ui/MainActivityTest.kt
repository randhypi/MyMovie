package com.randhypi.mymovie.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.randhypi.mymovie.R
import com.capstone.core.utils.DummyMovies
import com.capstone.core.utils.DummyTvShows
import com.capstone.core.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest{

    private val dummyMovies = DummyMovies.moviesDummy()
    private val dummyTvShows = DummyTvShows.tvShowsDummy()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
       onView(withId(R.id.rvMoviesList))
            .check(matches(isDisplayed()))
       onView(withId(R.id.rvMoviesList))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadDetailMovies() {
       onView(withId(R.id.rvMoviesList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
       onView(withId(R.id.tv_title))
            .check(matches(isDisplayed()))
       onView(withId(R.id.tv_title))
            .check(matches(withText(dummyMovies[0].title)))
       onView(withId(R.id.tv_release_date))
            .check(matches(isDisplayed()))
       onView(withId(R.id.tv_release_date))
            .check(matches(withText(dummyMovies[0].releaseDate)))
    }

    @Test
    fun loadFavMovies(){
        onView(withId(R.id.action_favorite)).perform(
                click()
            )

        onView(withId(R.id.rvMoviesFavList))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rvMoviesFavList))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadFavTv(){
        onView(withId(R.id.action_favorite)).perform(
            click()
        )

        onView(withText(R.string.tab_text_2)).perform(click())
        onView(withId(R.id.rvTvShowsFavList))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShowsFavList))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size))
    }

    @Test
    fun loadTvShows() {
        onView(withText(R.string.tab_text_2)).perform(click())
        onView(withId(R.id.rvTvShowsList))
            .check(matches(isDisplayed()))
       onView(withId(R.id.rvTvShowsList))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size))
    }


    @Test
    fun loadDetailTvShows() {
       onView(withText(R.string.tab_text_2)).perform(click())
       onView(withId(R.id.rvTvShowsList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
       onView(withId(R.id.tv_title))
            .check(matches(isDisplayed()))
       onView(withId(R.id.tv_title))
            .check(matches(withText(dummyTvShows[0].name)))
       onView(withId(R.id.tv_release_date))
            .check(matches(isDisplayed()))
       onView(withId(R.id.tv_release_date))
            .check(matches(withText(dummyTvShows[0].date)))
    }


    @Test
    fun clickFavTvShows() {
        onView(withText(R.string.tab_text_2)).perform(click())
        onView(withId(R.id.rvTvShowsList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.action_favorite)).perform(click())
    }

    @Test
    fun clickDelFavTvShows() {
        onView(withText(R.string.tab_text_2)).perform(click())
        onView(withId(R.id.rvTvShowsList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.action_favorite)).perform(click())
    }

    @Test
    fun clickFavMovies() {
        onView(withText(R.string.tab_text_1)).perform(click())
        onView(withId(R.id.rvMoviesList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.action_favorite)).perform(click())
    }

    @Test
    fun clickDelFavMovies() {
        onView(withText(R.string.tab_text_1)).perform(click())
        onView(withId(R.id.rvMoviesList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.action_favorite)).perform(click())
    }

}
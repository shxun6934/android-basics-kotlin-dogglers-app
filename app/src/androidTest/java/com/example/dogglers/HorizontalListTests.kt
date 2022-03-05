/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.dogglers.BaseTest.DrawableMatcher.hasItemCount
import com.example.dogglers.data.DataSource
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class HorizontalListTests : BaseTest() {

    @get:Rule
    var activityRule: ActivityScenarioRule<HorizontalListActivity> =
        ActivityScenarioRule(HorizontalListActivity::class.java)

    @Test
    fun `horizontal_scroll_content_at_first_position`() {
        val firstDog = DataSource.dogs.first()
        checkFirstPosition(
            firstDog.name,
            "Age: ${firstDog.age}",
            "Hobbies: ${firstDog.hobbies}",
            firstDog.imageResourceId
        )
    }

    @Test
    fun `horizontal_scroll_content_at_last_position`() {
        val lastDogs = DataSource.dogs.last()

        scrollToLastPosition(R.id.horizontal_recycler_view)
        checkLastPosition(
            lastDogs.name,
            "Age: ${lastDogs.age}",
            "Hobbies: ${lastDogs.hobbies}",
            lastDogs.imageResourceId
        )
    }

    @Test
    fun `horizontal_scrolling`() {
        onView(withId(R.id.horizontal_recycler_view))
            .perform(swipeLeft())
        onView(withText("Frankie")).check(matches(isDisplayed()))
    }

    @Test
    fun `recycler_view_item_count`() {
        val dogs = DataSource.dogs
        onView(withId(R.id.horizontal_recycler_view)).check(hasItemCount(dogs.size))
    }
}

package com.acme.a3csci3130;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.R.attr.description;
import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.not;

/**
 * Created by Nathan on 2017-07-10.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class CreateAndReadTests {

    private String testNum, testName, testPrimary, testAddress, testLocation;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void init() {
        testNum = "555555555";
        testName = "TEST";
        testPrimary = "Processor";
        testAddress = "6050 University Ave.";
        testLocation = "NS";
    }

    /**
     * Create a new business with test values.
     */
    @Test
    public void createBusiness() {
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.num)).perform(typeText(testNum));
        onView(withId(R.id.name)).perform(typeText(testName));
        onView(withId(R.id.primary)).perform(typeText(testPrimary));
        onView(withId(R.id.address)).perform(typeText(testAddress));
        onView(withId(R.id.location)).perform(typeText(testLocation));
        closeSoftKeyboard();
        onView(withId(R.id.submitButton)).perform(click());
    }

    /**
     * Read a business and assert that the first three properties are not empty
     */
    @Test
    public void readBusiness() {
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.num)).check(matches(not(withText(""))));
        onView(withId(R.id.name)).check(matches(not(withText(""))));
        onView(withId(R.id.primary)).check(matches(not(withText(""))));
    }

}

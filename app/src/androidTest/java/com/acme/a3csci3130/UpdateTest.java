package com.acme.a3csci3130;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
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
public class UpdateTest {

    private String testNum, testName, testPrimary, testAddress, testLocation;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void init() {
        testNum = "332941863";
        testName = "Updated Business";
        testPrimary = "Fish Monger";
        testAddress = "6050 Updated Ave.";
        testLocation = "ON";
    }

    /**
     * Updates a business and verifies that the values are now updated.
     */
    @Test
    public void updateBusiness() {
        //click on the first business and update it
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.num)).perform(replaceText(testNum));
        onView(withId(R.id.name)).perform(replaceText(testName));
        onView(withId(R.id.primary)).perform(replaceText(testPrimary));
        onView(withId(R.id.address)).perform(replaceText(testAddress));
        onView(withId(R.id.location)).perform(replaceText(testLocation));
        closeSoftKeyboard();

        //click the updated business
        onView(withId(R.id.updateButton)).perform(click());

        //confirm that it is in fact updated
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.num)).check(matches(withText(testNum)));
        onView(withId(R.id.name)).check(matches(withText(testName)));
        onView(withId(R.id.primary)).check(matches(withText(testPrimary)));
        onView(withId(R.id.address)).check(matches(withText(testAddress)));
        onView(withId(R.id.location)).check(matches(withText(testLocation)));
    }



}

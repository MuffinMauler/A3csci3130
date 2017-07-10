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
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;

/**
 * Created by Nathan on 2017-07-10.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class DeleteTest {

    private String testNum, testName, testPrimary, testAddress, testLocation;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void init() {
        testNum = "000000000";
        testName = "Delete Me";
        testPrimary = "Fisher";
        testAddress = "6050 University Ave.";
        testLocation = "PE";
    }

    /**
     * Create a new business with test values.
     * This is to avoid errors in case the list view is empty.
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
     * Deletes the first business in the list view.
     */
    @Test
    public void deleteBusiness() {
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());

        //click the delete business
        onView(withId(R.id.deleteButton)).perform(click());
    }

}

package com.acme.a3csci3130;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.core.AllOf.allOf;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;

/**
 * This class has the espresso test for the Create, Read, Update and Delete operations
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@LargeTest
public class CRUDTests {

    @Rule
    public ActivityTestRule<MainActivity>  mainActivityRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * This tests the create operation and creates a new contact
     */
    @Test
    public void A_createTest(){
        onView(withId(R.id.submitButton)).perform(click());

        onView(withId(R.id.name)).perform(typeText("EsspressoTest"),closeSoftKeyboard());

        onView(withId(R.id.email)).perform(typeText("EsspressoTest@test.com"),closeSoftKeyboard());

        onView(withId(R.id.number)).perform(typeText("123456789"),closeSoftKeyboard());

        onView(withId(R.id.business)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Fish Monger"))).perform(click());
        onView(withId(R.id.business)).check(matches(withSpinnerText(containsString("Fish Monger"))));

        onView(withId(R.id.address)).perform(typeText("123 Test Drive"),closeSoftKeyboard());

        onView(withId(R.id.province)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("NL"))).perform(click());
        onView(withId(R.id.province)).check(matches(withSpinnerText(containsString("NL"))));

        onView(withId(R.id.submitButton)).perform(click());
    }

    /**
     * This test the read operations and reads some of the fields.
     * I was not able to get the spinners to be read
     */
    @Test
    public void B_ReadTest(){
//        try
//        {
//            Thread.sleep(1000);
//        }
//        catch(InterruptedException ex)
//        {
//            Thread.currentThread().interrupt();
//        }
        onView(allOf(withText("EsspressoTest"))).perform(click());

        onView(withId(R.id.email)).check(matches(withText("EsspressoTest@test.com")));
        onView(withId(R.id.number)).check(matches(withText("123456789")));

        onView(withId(R.id.address)).check(matches(withText("123 Test Drive")));


    }

    /**
     * This tests the update operations and updates the new object that was made in the first test
     */
    @Test
    public void C_UpdateTest(){
//        try
//        {
//            Thread.sleep(1000);
//        }
//        catch(InterruptedException ex)
//        {
//            Thread.currentThread().interrupt();
//        }
        onView(allOf(withText("EsspressoTest"))).perform(click());

        onView(withId(R.id.name)).perform(replaceText("UpdateTest"),closeSoftKeyboard());

        onView(withId(R.id.email)).perform(replaceText("UpdateTest@test.com"),closeSoftKeyboard());

        onView(withId(R.id.number)).perform(replaceText("987654321"),closeSoftKeyboard());

        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Fisher"))).perform(click());
        onView(withId(R.id.spinner)).check(matches(withSpinnerText(containsString("Fisher"))));

        onView(withId(R.id.address)).perform(replaceText("321 Update Street"),closeSoftKeyboard());

        onView(withId(R.id.province)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("NS"))).perform(click());
        onView(withId(R.id.province)).check(matches(withSpinnerText(containsString("NS"))));
        onView(withId(R.id.updateButton)).perform(click());
    }

    /**
     * This tests the delete and deletes the contact that was created
     */
    @Test
    public void D_deleteTest(){

        onView(allOf(withText("UpdateTest"))).perform(click());
        onView(withId(R.id.deleteButton)).perform(click());
    }

}

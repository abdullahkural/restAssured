package com.cybertek.Day05;


import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;



public class HamcrestMatchersIntro {

    @DisplayName("Assertions with numbers")
    @Test
    public void simpleTest1(){

        //actual 5+5
        MatcherAssert.assertThat(5+5, Matchers.is(10));
        assertThat(5+5, Matchers.is(10));
        assertThat(5+5, Matchers.equalTo(10));
        assertThat(5+5, Matchers.is(Matchers.equalTo(10)));

        //matchers has 2 overloaded version
        //first that accept actual value
        //second that accept another matchers
        //below examples is() method is accepting another matchers equal to make it readable
        assertThat(2+2, is(equalTo(4)));

        //negative testing
        assertThat(5+5, not(9));
        assertThat(5+5, is(not(9)));
        assertThat(5+5, is(not(equalTo(9))));

        /*
        number comparison
        greaterThan()
        greaterThanOrEqualTo()
        lessThan()
        lessThanOrEqualTo()
         */

        assertThat(5+5, is(greaterThan(9)));
        assertThat(5+5,is(greaterThanOrEqualTo(10)));
        assertThat(5+5,is(lessThan(11)));
        assertThat(5+5,is(lessThanOrEqualTo(11)));

    }

    @DisplayName("Assertions with String")
    @Test
    public void stringHamcrest(){

        String text = "I will earn too much";

        //checking equality is same as numbers
        assertThat(text, is("I will earn too much"));
        assertThat(text, equalTo("I will earn too much"));
        assertThat(text, is(equalTo("I will earn too much")));

        //check if this starts with "I"
        assertThat(text, startsWith("I"));
        //now do it in case insensetive
        assertThat(text, startsWithIgnoringCase("i"));

        //ends with
        assertThat(text, endsWith("much"));
        assertThat(text,endsWithIgnoringCase("MuCh"));

        //check if text contains String learning
        assertThat(text, containsString("earn"));
        //with ignoring case
        assertThat(text,containsStringIgnoringCase("TOO"));

        String str = "";

        //check if above str is blank
        assertThat(str, is(blankString()));
        assertThat(str, blankString());
        //check if trimmed str is empty String
        assertThat(str, is(emptyString()));
        assertThat(str, emptyString());

    }


    @DisplayName("Hamcrest for Collection")
    @Test
    public void testCollection(){
        List<Integer> listOfNumbers = Arrays.asList(1,4,5,6,32,54,66,77,45,23);

        //check size of the list
        assertThat(listOfNumbers, hasSize(10));
        //check if this list hasItem(77)
        assertThat(listOfNumbers, hasItem(77));

        //check if this list hasItems 77,54,23
        assertThat(listOfNumbers, hasItems(77,54,23));

        //check if all numbers are greater than 0
        assertThat(listOfNumbers, everyItem(greaterThan(0)));
        assertThat(listOfNumbers, everyItem(is(lessThan(99))));
    }


}

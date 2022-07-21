package com.cybertek.Day05;


import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;



public class HamcrestMatchersIntro {

    @Test
    public void simpleTest1(){

        //actual 5+5
        MatcherAssert.assertThat(5+5, Matchers.is(10));
        assertThat(5+5, Matchers.is(10));
        assertThat(5+5, Matchers.equalTo(10));
        assertThat(5+5, Matchers.is(Matchers.equalTo(10)));

        /*
        The instructor asserted like:
        assertThat(5+5, is(10));
        assertThat(5+5, equalTo(10));
        assertThat(5+5, is(equalTo(10)));

        But I could not. I can not import
         */

    }
}

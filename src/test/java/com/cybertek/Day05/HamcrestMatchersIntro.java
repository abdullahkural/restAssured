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

        //matchers has 2 overloaded version
        //first that accept actual value
        //second that accept another matchers
        //below examples is() method is accepting another matchers equal to make it readable
        assertThat(2+2, is(equalTo(4)));

        //negative testing
        assertThat(5+5, not(9));
        assertThat(5+5, is(not(9)));
        assertThat(5+5, is(not(equalTo(9))));

        //number comparison





    }
}

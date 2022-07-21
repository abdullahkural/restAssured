package com.cybertek.Day05;


import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class HamcrestMatchersIntro {

    @Test
    public void simpleTest1(){

        MatcherAssert.assertThat(5+5, Matchers.is(10));
    }
}

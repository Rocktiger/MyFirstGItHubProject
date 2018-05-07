package com.nb.mengbiao.myfirstgithubproject;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        List<String> list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.remove(1);
        for (String s : list) {
            System.out.println(s +"----" +list.indexOf(s));
        }
    }
}
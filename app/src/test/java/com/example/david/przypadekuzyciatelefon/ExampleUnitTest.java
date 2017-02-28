package com.example.david.przypadekuzyciatelefon;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    List<String> dates = new ArrayList<>();

    @Test
    public void date_isCorrect() throws Exception {
        boolean goodPattern = true;
        Matcher matcher;
        addDates();
        String regex = "\\A(?:2100|20[0-9]{2})-(?:0?2-(?:[12][0-9]|0?[1-9])|(?:0?[469]|11)-(?:30|[12][0-9]|0?[1-9])|(?:0?[13578]|1[02])-(?:3[01]|[12][0-9]|0?[1-9]))[ \\t]+(?:2[0-3]|[01]?[0-9])[:.][0-5]?[0-9][:.][0-5]?[0-9]\\b";
        try {
            Pattern patt = Pattern.compile(regex);
            for (String date : dates) {
                matcher = patt.matcher(date);
                goodPattern = matcher.matches();
            }
        } catch (RuntimeException e) {
            goodPattern = false;
        }

        assertEquals(true, goodPattern);
    }

    private void addDates() {
        dates.add("2017-09-03 12:12:00");
        dates.add("2016-02-29 23:59:59");
        dates.add("2015-07-31 00:00:00");
        dates.add("2015-08-31 01:15:11");
    }
}
package com.codeanalysis.jdk.lambda;
import java.util.Calendar;

import static java.lang.System.out;
/**
 * @author Gavin
 * @date 2020/9/6
 */
public final class DemonstrateSyntheticMethods
{
    public static void main(final String[] arguments)
    {
        DemonstrateSyntheticMethods.NestedClass nested =
                new DemonstrateSyntheticMethods.NestedClass();
        out.println("String: " + nested.highlyConfidential);
    }

    private static final class NestedClass
    {
        private String highlyConfidential = "Don't tell anyone about me";
        private int highlyConfidentialInt = 42;
        private Calendar highlyConfidentialCalendar = Calendar.getInstance();
        private boolean highlyConfidentialBoolean = true;
    }
}

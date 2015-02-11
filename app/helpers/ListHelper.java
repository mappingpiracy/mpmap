package helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alex on 2/11/15.
 */
public class ListHelper {
    /*
        Convert a comma-separated string to a list of Integers.
     */
    public static List<Integer> commaStringToIntegerList(String values) {
        List<Integer> finalValues = new ArrayList<Integer>();
        List<String> stringValues;

        if(!values.isEmpty()) {
            stringValues = Arrays.asList(values.split(","));
            for(String s : stringValues) {
                try {
                    finalValues.add(new Integer(s));
                } catch (NumberFormatException e) {
                    finalValues.clear();
                    return finalValues;
                }
            }
        }
        return finalValues;
    }

    /*
        Convert a comma-separated string to a list of Strings
        Returns an empty list in the case of an empty string
     */
    public static List<String> commaStringToStringList(String values) {
        List<String> finalValues;
        if(values.isEmpty()) {
            finalValues = new ArrayList<String>();
        } else {
            finalValues = Arrays.asList(values.split((",")));
        }
        return finalValues;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package richBank.util;

/**
 *
 * @author terry
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

    // Date formt patttern
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    //Formatter
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    //Returns date as well as string
    public static String format(LocalDate date) {

        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    //CHANGES TO THE FORMATT IF NOT POSSABLE IT WILL RETURN NULL
    public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    //Checks if its a vaild date string
    public static boolean vaildDate(String dateString) {
        return DateUtil.parse(dateString) != null;
    }
}

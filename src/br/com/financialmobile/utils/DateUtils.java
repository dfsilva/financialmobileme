package br.com.financialmobile.utils;

import java.util.Calendar;
import java.util.Date;


/**
 *
 * @author diego
 */
public class DateUtils {

    public static Date stringToDate(String arg0) {
        Calendar cal = Calendar.getInstance();

        String[] data = StringUtils.split(arg0, "/");
        cal.set(Calendar.DATE, Integer.valueOf(data[0]).intValue());
        cal.set(Calendar.MONTH, Integer.valueOf(data[1]).intValue()-1);
        cal.set(Calendar.YEAR, Integer.valueOf(data[2]).intValue());
        return cal.getTime();
    }

    public static String getDateStringddMMYYYY(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return  day+"/"+month+"/"+year;
    }
/**
    Date createDate(String dateString) {
        //Extract needed numbers first
        StringBuffer strBuf = new StringBuffer(dateString);

        char[] charBuf = new char[4];

        strBuf.getChars(0, 3, charBuf, 0);
        dayOfWeek = new String(charBuf, 0, 3);

        strBuf.getChars(strBuf.length() - 4, strBuf.length(), charBuf, 0);
        year = charsToInt(charBuf, 4);

        strBuf.getChars(4, 7, charBuf, 0);
        String monthStr = new String(charBuf, 0, 3);
        month = ArraySearcher.searchStringEntry(monthStr, MONTHS);

        day = extractShortField(strBuf, 8, charBuf);
        hour = extractShortField(strBuf, 11, charBuf);
        minute = extractShortField(strBuf, 14, charBuf);
        second = extractShortField(strBuf, 17, charBuf);

        //Now set the calendar
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);

        return calendar.getTime();
    }

    private int charsToInt(char[] buf, int len) {
        String str = new String(buf, 0, len);
        return Integer.parseInt(str);
    }

    private int extractShortField(StringBuffer strBuf, int start, char[] charBuf) {
        strBuf.getChars(start, start + 2, charBuf, 0);
        return charsToInt(charBuf, 2);
    }
 * **/
}

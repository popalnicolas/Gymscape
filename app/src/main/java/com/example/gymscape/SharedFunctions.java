package com.example.gymscape;

import com.example.gymscape.R;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SharedFunctions {
    public static int getDate(long date)
    {
        Date date1 = new Date(date);
        String myFormat = "ddMMyyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.UK);
        String desiredFormat = dateFormat.format(date1);
        return Integer.parseInt(desiredFormat);
    }

    public static int getIcon(int category)
    {
        int icon = 0;
        switch (category)
        {
            case 1:
                return R.drawable.icon_core;
            case 2:
                return R.drawable.icon_chest;
            case 3:
                return R.drawable.icon_back;
            case 4:
                return R.drawable.icon_biceps;
            case 5:
                return R.drawable.icon_triceps;
            case 6:
                return R.drawable.icon_shoulder;
            case 7:
                return R.drawable.icon_legs;
            case 8:
                return R.drawable.icon_glutes;
        }
        return icon;
    }

    public static boolean isEmailValid(String email)
    {
        Pattern email_regex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = email_regex.matcher(email.trim());
        return matcher.find();
    }
}

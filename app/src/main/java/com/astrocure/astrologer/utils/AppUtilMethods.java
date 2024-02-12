package com.astrocure.astrologer.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AppUtilMethods {
    private static final long SCHEDULE_TIME = 5L;

    public static float dpFromPx(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static String getTimeAgo(String DATE, String PATTERN) throws ParseException {
        String timeAgo;
        Date currDate = new Date();
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat(PATTERN);
        Date inDate = dateFormat.parse(DATE);
        long diffInMillies = Math.abs(inDate.getTime() - currDate.getTime());
        long diffSecond = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        long diffMinute = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
        long diffHour = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        long diffDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        if (diffDays != 0 && diffDays <= 7) {
            timeAgo = diffDays + " days ago";
        } else if (diffHour != 0 && diffHour <= 24) {
            timeAgo = diffHour + " hr ago";
        } else if (diffMinute != 0 && diffMinute <= 60) {
            timeAgo = diffMinute + " min ago";
        } else if (diffSecond != 0 && diffSecond <= 60) {
            timeAgo = diffSecond + " sec ago";
        } else {
            timeAgo = new PrettyTime().format(inDate);
        }

        return timeAgo;
    }

    public static String prettyCount(Number number) {
        char[] suffix = {' ', 'k', 'M', 'B', 'T', 'P', 'E'};
        long numValue = number.longValue();
        int value = (int) Math.floor(Math.log10(numValue));
        int base = value / 3;
        if (value >= 3 && base < suffix.length) {
            return new DecimalFormat("#0.0").format(numValue / Math.pow(10, base * 3)) + suffix[base];
        } else {
            return new DecimalFormat("#,##0").format(numValue);
        }
    }

}

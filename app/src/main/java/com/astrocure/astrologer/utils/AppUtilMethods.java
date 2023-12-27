package com.astrocure.astrologer.utils;

import android.content.Context;

public class AppUtilMethods {
    private static final long SCHEDULE_TIME = 5L;

    public static float dpFromPx(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

}

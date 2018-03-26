package com.akinadude.cloudfactory.utils;

import android.content.Context;
import android.os.Build;

public class ResourceUtils {

    public static int getColor(Context c, int colorRes) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return c.getResources().getColor(colorRes);
        else
            return c.getResources().getColor(colorRes, c.getTheme());
    }
}

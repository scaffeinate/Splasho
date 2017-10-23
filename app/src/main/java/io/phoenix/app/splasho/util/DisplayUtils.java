package io.phoenix.app.splasho.util;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by sudharti on 10/22/17.
 */

public final class DisplayUtils {

    private DisplayMetrics mDisplayMetrics;
    private static DisplayUtils mDisplayUtils;

    private DisplayUtils(Activity activity) {
        mDisplayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
    }

    public static DisplayUtils getInstance(Activity activity) {
        if (mDisplayUtils == null) {
            mDisplayUtils = new DisplayUtils(activity);
        }

        return mDisplayUtils;
    }

    public int getScreenHeight() {
        return mDisplayUtils.mDisplayMetrics.heightPixels;
    }

    public int getScreenWidth() {
        return mDisplayUtils.mDisplayMetrics.widthPixels;
    }
}

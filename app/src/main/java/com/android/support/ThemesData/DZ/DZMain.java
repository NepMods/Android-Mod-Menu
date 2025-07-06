package com.android.support.ThemesData.DZ;

import android.content.Context;
import com.android.support.BuildConfig;
import com.android.support.interfaces.IMenuData;

public class DZMain {
    DZShared shared;

    public DZMain(Context context, DZShared shared) {
        this.shared = shared;

        // Only run if DZ theme is enabled
        if (BuildConfig.INCLUDE_THEME_DZ) {
            try {
                Class<?> clazz = Class.forName("com.android.support.ThemesData.DZ.DZInitializer");
                clazz.getMethod("initialize", Context.class, DZShared.class).invoke(null, context, shared);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setData(IMenuData data) {
        this.shared.MenuData = data;
    }
}

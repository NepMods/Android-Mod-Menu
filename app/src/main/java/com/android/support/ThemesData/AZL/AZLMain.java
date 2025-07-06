package com.android.support.ThemesData.AZL;

import android.content.Context;
import com.android.support.BuildConfig;
import com.android.support.interfaces.IMenuData;

public class AZLMain {
    AZLShared shared;

    public AZLMain(Context context, AZLShared shared) {
        this.shared = shared;

        // Only run if AZL theme is enabled
        if (BuildConfig.INCLUDE_THEME_AZL) {
            try {
                Class<?> clazz = Class.forName("com.android.support.ThemesData.AZL.AZLInitializer");
                clazz.getMethod("initialize", Context.class, AZLShared.class).invoke(null, context, shared);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setData(IMenuData data) {
        this.shared.MenuData = data;
    }
}

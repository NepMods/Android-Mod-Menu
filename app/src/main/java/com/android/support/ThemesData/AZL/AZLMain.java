package com.android.support.ThemesData.AZL;

import android.content.Context;
import com.android.support.interfaces.IMenuData;
import com.android.support.BuildConfig;

public class AZLMain {

    AZLShared shared;

    public AZLMain(Context context, AZLShared shared) {
        this.shared = shared;

        // Only run if AZL theme is enabled
        if (BuildConfig.INCLUDE_THEME_AZL) {
            this.shared.MenuStyle = new AZLStyle();
            this.shared.getContext = context;
            this.shared.components = new AZLMenuComponents(shared);
            this.shared.menu = new AZLMenu(shared);
        }
    }

    public void setData(IMenuData data) {
        this.shared.MenuData = data;
    }
}

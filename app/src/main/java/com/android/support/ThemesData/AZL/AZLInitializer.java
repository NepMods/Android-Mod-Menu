package com.android.support.ThemesData.AZL;

import android.content.Context;

public class AZLInitializer {
    public static void initialize(Context context, AZLShared shared) {
        shared.MenuStyle = new AZLStyle();
        shared.getContext = context;
        shared.components = new AZLMenuComponents(shared);
        shared.menu = new AZLMenu(shared);
    }
}

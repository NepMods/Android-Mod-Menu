package com.android.support.ThemesData.DZ;

import android.content.Context;

public class DZInitializer {
    public static void initialize(Context context, DZShared shared) {
        shared.MenuStyle = new DZStyle();
        shared.getContext = context;
        shared.components = new DZMenuComponents(shared);
        shared.menu = new DZMenu(shared);
    }
}

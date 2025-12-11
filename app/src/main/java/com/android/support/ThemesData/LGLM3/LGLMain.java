package com.android.support.ThemesData.LGLM3;

import android.content.Context;

import com.android.support.interfaces.IMenuData;

public class LGLMain {
    LGLShared shared;

    public LGLMain(Context context, LGLShared shared) {
        this.shared = shared;
        this.shared.MenuStyle = new  LGLStyle();
        this.shared.getContext = context;
        this.shared.components = new LGLMenuComponents(shared);
        this.shared.menu = new LGLMenu(shared);
    }

    public void setData(IMenuData data) {
        this.shared.MenuData = data;
    }
}

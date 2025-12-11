package com.android.support.ThemesData.LGLM3;

import android.content.Context;

import com.android.support.interfaces.IMenuData;

public class LGLMainM3 {
    LGLSharedM3 shared;

    public LGLMainM3(Context context, LGLSharedM3 shared) {
        this.shared = shared;
        this.shared.MenuStyle = new  LGLStyleM3();
        this.shared.getContext = context;
        this.shared.components = new LGLMenuM3Components(shared);
        this.shared.menu = new LGLMenuM3(shared);
    }

    public void setData(IMenuData data) {
        this.shared.MenuData = data;
    }
}

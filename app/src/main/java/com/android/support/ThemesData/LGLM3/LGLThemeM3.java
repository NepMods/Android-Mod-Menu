package com.android.support.ThemesData.LGLM3;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.textview.MaterialTextView;

import com.android.support.Menu;
import com.android.support.base.FloatingWindowManager;
import com.android.support.interfaces.IMenuData;
import com.android.support.interfaces.IMenuTheme;

public class LGLThemeM3 implements IMenuTheme {
    FloatingWindowManager windowManager;
    LGLSharedM3 shared;
    /**
     *
     */
    @Override
    public void Init(Context context, final IMenuData menuData) {
        shared = new LGLSharedM3();
        windowManager = new FloatingWindowManager(context, shared);
        LGLMainM3 main = new LGLMainM3(context, shared);
        main.setData(new IMenuData() {
            @Override
            public String Icon() {
                return menuData.Icon();
            }

            @Override
            public String IconWebViewData() {
                return menuData.IconWebViewData();
            }

            @Override
            public String[] GetFeatureList() {
                return menuData.GetFeatureList();
            }

            @Override
            public String[] SettingsList() {
                return menuData.SettingsList();
            }

            @Override
            public boolean IsGameLibLoaded() {
                return menuData.IsGameLibLoaded();
            }

            @Override
            public View.OnTouchListener onTouchListener() {
                return windowManager.onTouchListener();
            }

            @Override
            public void Init(Context context, TextView title, TextView subTitle) {
                // Кастуем на MaterialTextView, если уверены, что это безопасно
            menuData.Init(context, (MaterialTextView) title, (MaterialTextView) subTitle);
            }
        });
        shared.menu.build();
    }

    /**
     *
     */
    @Override
    public void ShowMenu() {
        shared.menu.show();
    }

    /**
     * @param view
     */
    @Override
    public void setVisibility(int view) {
        shared.menu.setVisibility(view);
    }

    /**
     *
     */
    @Override
    public void onDestroy() {
        shared.menu.destroy();
    }

    /**
     *
     */
    @Override
    public void SetWindowManagerWindowService() {
        windowManager.SetWindowManagerWindowService();
    }

    /**
     *
     */
    @Override
    public void SetWindowManagerActivity() {

        windowManager.SetWindowManagerActivity();
    }
}

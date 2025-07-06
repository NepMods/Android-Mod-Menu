//Please don't replace listeners with lambda!

package com.android.support;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.android.support.ThemesData.DZ.DZTheme;
import com.android.support.ThemesData.LGL.LGLMain;
import com.android.support.ThemesData.LGL.LGLShared;
import com.android.support.ThemesData.LGL.LGLTheme;
import com.android.support.base.FloatingWindowManager;
import com.android.support.interfaces.IMenuData;

public class Menu extends LGLTheme {
    native void Init(Context context, TextView title, TextView subTitle);

    native String Icon();

    native String IconWebViewData();

    native String[] GetFeatureList();

    native String[] SettingsList();

    native boolean IsGameLibLoaded();

    public Menu(Context context) {
        super.Init(context, new IMenuData() {
            /**
             * @return
             */
            @Override
            public String Icon() {
                return Menu.this.Icon();
            }

            /**
             * @return
             */
            @Override
            public String IconWebViewData() {
                return Menu.this.IconWebViewData();
            }

            /**
             * @return
             */
            @Override
            public String[] GetFeatureList() {
                return Menu.this.GetFeatureList();
            }

            /**
             * @return
             */
            @Override
            public String[] SettingsList() {
                return Menu.this.SettingsList();
            }

            /**
             * @return
             */
            @Override
            public boolean IsGameLibLoaded() {
                return Menu.this.IsGameLibLoaded();
            }

            /**
             * @return
             */
            @Override
            public View.OnTouchListener onTouchListener() {
                return  null; //Later to be initialized
            }

            /**
             * @param context
             * @param title
             * @param subTitle
             */
            @Override
            public void Init(Context context, TextView title, TextView subTitle) {
                Menu.this.Init(context, title, subTitle);
            }
        });
    }
}
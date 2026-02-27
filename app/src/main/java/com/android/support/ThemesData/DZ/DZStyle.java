package com.android.support.ThemesData.DZ;

import android.graphics.Color;
import com.android.support.interfaces.IMenuStyle;

public class DZStyle extends IMenuStyle {

    public static final String TAG = "Mod_Menu";

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public int getTextColor() {
        return Color.parseColor("#00FFFF");
    }
    // Extra method for NEW_COLOR (not in interface)
    public int getNewColor() {
        return Color.parseColor("#000000");
    }

    @Override
    public int getTextColor2() {
        return Color.parseColor("#FFFFFF");
    }

    @Override
    public int getBtnColor() {
        return Color.parseColor("#1C262D");
    }

    @Override
    public int getMenuBgColor() {
        return Color.parseColor("#FF000000"); // #AARRGGBB
    }

    @Override
    public int getMenuFeatureBgColor() {
        return Color.parseColor("#FF000000");
    }

    @Override
    public int getMenuWidth() {
        return 230;
    }

    @Override
    public int getMenuHeight() {
        return 210;
    }

    @Override
    public float getMenuCorner() {
        return 4f;
    }

    @Override
    public int getIconSize() {
        return 57;
    }

    @Override
    public float getIconAlpha() {
        return 50.0f;
    }

    @Override
    public int getToggleON() {
        return Color.CYAN;
    }

    @Override
    public int getToggleOFF() {
        return Color.WHITE;
    }

    @Override
    public int getBtnON() {
        return Color.parseColor("#00FFFF");
    }

    @Override
    public int getBtnOFF() {
        return Color.parseColor("#7f0000");
    }

    @Override
    public int getCategoryBG() {
        return Color.parseColor("#00FFFF");
    }

    @Override
    public int getSeekBarColor() {
        return Color.parseColor("#00FFFF");
    }

    @Override
    public int getSeekBarProgressColor() {
        return Color.parseColor("#00FFFF");
    }

    @Override
    public int getCheckBoxColor() {
        return Color.parseColor("#00FFFF");
    }

    @Override
    public int getRadioColor() {
        return Color.parseColor("#00FFFF");
    }

    @Override
    public int getCollapseColor() {
        return Color.parseColor("#00FFFF"); // You can change this if needed
    }

    @Override
    public String getNumberTxtColor() {
        return "#00FFFF";
    }
}

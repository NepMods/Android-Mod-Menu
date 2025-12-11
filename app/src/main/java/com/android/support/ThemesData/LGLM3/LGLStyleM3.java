package com.android.support.ThemesData.LGLM3;
import android.graphics.Color;

import com.android.support.interfaces.IMenuStyle;

public class LGLStyleM3 extends IMenuStyle {

    @Override
    public String getTag() {
        return "Mod_Menu";
    }
    @Override
    public int getTextColor() {
        return Color.parseColor("#82CAFD");
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
        return Color.parseColor("#EE1C2A35");
    }

    @Override
    public int getMenuFeatureBgColor() {
        return Color.parseColor("#DD141C22");
    }

    @Override
    public int getMenuWidth() {
        return 290;
    }

    @Override
    public int getMenuHeight() {
        return 210;
    }

    @Override
    public int getPosX() {
        return 0;
    }

    @Override
    public int getPosY() {
        return 100;
    }

    @Override
    public float getMenuCorner() {
        return 4f;
    }

    @Override
    public int getIconSize() {
        return 45;
    }

    @Override
    public float getIconAlpha() {
        return 0.7f;
    }

    @Override
    public int getToggleON() {
        return Color.GREEN;
    }

    @Override
    public int getToggleOFF() {
        return Color.RED;
    }

    @Override
    public int getBtnON() {
        return Color.parseColor("#1b5e20");
    }

    @Override
    public int getBtnOFF() {
        return Color.parseColor("#7f0000");
    }

    @Override
    public int getCategoryBG() {
        return Color.parseColor("#2F3D4C");
    }

    @Override
    public int getSeekBarColor() {
        return Color.parseColor("#80CBC4");
    }

    @Override
    public int getSeekBarProgressColor() {
        return Color.parseColor("#80CBC4");
    }

    @Override
    public int getCheckBoxColor() {
        return Color.parseColor("#80CBC4");
    }

    @Override
    public int getRadioColor() {
        return Color.parseColor("#FFFFFF");
    }

    @Override
    public int getCollapseColor() {
        return Color.parseColor("#232F2C");
    }

    @Override
    public String getNumberTxtColor() {
        return "#41c300";
    }
}

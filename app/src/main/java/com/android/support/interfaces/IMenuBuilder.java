package com.android.support.interfaces;

import android.widget.LinearLayout;

public interface IMenuBuilder {
    void build();
    void show();
    void buildFeature(String[] listFT, LinearLayout linearLayout);

    void destroy();
    void setVisibility(int visibility);
}

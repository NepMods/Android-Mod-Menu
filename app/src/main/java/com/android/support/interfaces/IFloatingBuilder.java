package com.android.support.interfaces;

import android.view.View;

public interface IFloatingBuilder
{
    void SetWindowManagerWindowService();
    void SetWindowManagerActivity();
    View.OnTouchListener onTouchListener();

}

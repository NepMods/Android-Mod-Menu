package com.android.support.ThemesData.LGLM3;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.RelativeLayout.ALIGN_PARENT_LEFT;
import static android.widget.RelativeLayout.ALIGN_PARENT_RIGHT;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Base64;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import android.widget.Toast;

import com.android.support.Preferences;
import com.android.support.interfaces.IMenuBuilder;

public class LGLMenuM3 implements IMenuBuilder {
    LGLSharedM3 sharedData;

    public LGLMenuM3(LGLSharedM3 shared){
        sharedData = shared;
        Preferences.context = shared.getContext;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void build() {
        sharedData.rootFrame = new FrameLayout(sharedData.getContext); // Global markup
        sharedData.rootFrame.setOnTouchListener(sharedData.MenuData.onTouchListener());
        sharedData.mRootContainer = new RelativeLayout(sharedData.getContext); // Markup on which two markups of the icon and the menu itself will be placed
        sharedData.mCollapsed = new RelativeLayout(sharedData.getContext); // Markup of the icon (when the menu is minimized)
        sharedData.mCollapsed.setVisibility(View.VISIBLE);
        sharedData.mCollapsed.setAlpha(sharedData.MenuStyle.getIconAlpha());


        //Build the Box
        buildTheBox();;

        //Build The Icons
        buildOpenIcon();
        WebView wView = buildOpenIconWeb();

        //Build Settings
        TextView settings = buildSettings();

        //********** Settings **********
        sharedData.mSettings = new LinearLayout(sharedData.getContext);
        sharedData.mSettings.setOrientation(LinearLayout.VERTICAL);
        buildFeature(sharedData.MenuData.SettingsList(), sharedData.mSettings);


        //********** Title **********
        RelativeLayout titleText = new RelativeLayout(sharedData.getContext);
        titleText.setPadding(10, 5, 10, 5);
        titleText.setVerticalGravity(16);


        MaterialTextView title = buildTitle();
        MaterialTextView subTitle = buildSubTitle();

        sharedData.scrollView = new ScrollView(sharedData.getContext);
        //Auto size. To set size manually, change the width and height example 500, 500
        sharedData.scrlLL = new LinearLayout.LayoutParams(MATCH_PARENT, dp(sharedData.MenuStyle.getMenuHeight()));
        sharedData.scrlLLExpanded = new LinearLayout.LayoutParams(sharedData.mExpanded.getLayoutParams());
        sharedData.scrlLLExpanded.weight = 1.0f;
        sharedData.scrollView.setLayoutParams(Preferences.isExpanded ?sharedData.scrlLLExpanded : sharedData.scrlLL);
        sharedData.scrollView.setBackgroundColor(sharedData.MenuStyle.getMenuFeatureBgColor());
        sharedData.mods = new LinearLayout(sharedData.getContext);
        sharedData.mods.setOrientation(LinearLayout.VERTICAL);

        //********** RelativeLayout for buttons **********
        RelativeLayout relativeLayout = new RelativeLayout(sharedData.getContext);
        relativeLayout.setPadding(10, 3, 10, 3);
        relativeLayout.setVerticalGravity(Gravity.CENTER);

        MaterialButton hideBtn = buildHideButton();
        MaterialButton closeBtn = buildCloseButton();

        //********** Adding view components **********
        sharedData.mRootContainer.addView(sharedData.mCollapsed);
        sharedData.mRootContainer.addView(sharedData.mExpanded);
        if (sharedData.MenuData.IconWebViewData() != null) {
            sharedData.mCollapsed.addView(wView);
        } else {
            sharedData.mCollapsed.addView(sharedData.startimage);
        }
        titleText.addView(title);
        titleText.addView(settings);
        sharedData.mExpanded.addView(titleText);
        sharedData.mExpanded.addView(subTitle);
        sharedData.scrollView.addView(sharedData.mods);
        sharedData.mExpanded.addView(sharedData.scrollView);
        relativeLayout.addView(hideBtn);
        relativeLayout.addView(closeBtn);
        sharedData.mExpanded.addView(relativeLayout);
        sharedData.MenuData.Init(sharedData.getContext, title, subTitle);
    }

    void buildTheBox() {
        //********** The box of the mod menu **********
        sharedData.mExpanded = new LinearLayout(sharedData.getContext); // Menu markup (when the menu is expanded)
        sharedData.mExpanded.setVisibility(View.GONE);
        sharedData.mExpanded.setBackgroundColor(sharedData.MenuStyle.getMenuBgColor());
        sharedData.mExpanded.setOrientation(LinearLayout.VERTICAL);
        // sharedData.mExpanded.setPadding(1, 1, 1, 1); //So borders would be visible
        sharedData.mExpanded.setLayoutParams(new LinearLayout.LayoutParams(dp(sharedData.MenuStyle.getMenuWidth()), WRAP_CONTENT));
        GradientDrawable gdMenuBody = new GradientDrawable();
        gdMenuBody.setCornerRadius(sharedData.MenuStyle.getMenuBgColor()); //Set corner
        gdMenuBody.setColor(sharedData.MenuStyle.getMenuBgColor()); //Set background color
        gdMenuBody.setStroke(1, Color.parseColor("#32cb00")); //Set border
    }
    @SuppressLint("ClickableViewAccessibility")
    public void buildOpenIcon() {

        //********** The icon to open mod menu **********
        sharedData.startimage = new ImageView(sharedData.getContext);
        sharedData.startimage.setLayoutParams(new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        int applyDimension = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sharedData.MenuStyle.getIconSize(), sharedData.getContext.getResources().getDisplayMetrics()); //Icon size
        sharedData.startimage.getLayoutParams().height = applyDimension;
        sharedData.startimage.getLayoutParams().width = applyDimension;
        //sharedData.startimage.requestLayout();
        sharedData.startimage.setScaleType(ImageView.ScaleType.FIT_XY);
        byte[] decode = Base64.decode(sharedData.MenuData.Icon(), 0);
        sharedData.startimage.setImageBitmap(BitmapFactory.decodeByteArray(decode, 0, decode.length));
        ((ViewGroup.MarginLayoutParams) sharedData.startimage.getLayoutParams()).topMargin = convertDipToPixels(10);
        //Initialize event handlers for buttons, etc.
        sharedData.startimage.setOnTouchListener(sharedData.MenuData.onTouchListener());
        sharedData.startimage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sharedData.mCollapsed.setVisibility(View.GONE);
                sharedData.mExpanded.setVisibility(View.VISIBLE);
            }
        });


    }


    WebView buildOpenIconWeb() {
        //********** The icon in Webview to open mod menu **********
        WebView wView = new WebView(sharedData.getContext); //Icon size width=\"50\" height=\"50\"
        wView.setLayoutParams(new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        int applyDimension2 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sharedData.MenuStyle.getIconSize(), sharedData.getContext.getResources().getDisplayMetrics()); //Icon size
        wView.getLayoutParams().height = applyDimension2;
        wView.getLayoutParams().width = applyDimension2;
        wView.loadData("<html>" +
                "<head></head>" +
                "<body style=\"margin: 0; padding: 0\">" +
                "<img src=\"" + sharedData.MenuData.IconWebViewData() + "\" width=\"" + sharedData.MenuStyle.getIconSize() + "\" height=\"" + sharedData.MenuStyle.getIconSize() + "\" >" +
                "</body>" +
                "</html>", "text/html", "utf-8");
        wView.setBackgroundColor(0x00000000); //Transparent
        wView.setAlpha(sharedData.MenuStyle.getIconAlpha());
        wView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wView.setOnTouchListener(sharedData.MenuData.onTouchListener());
        return wView;
    }
    MaterialTextView buildSettings() {
        //********** Settings icon **********
        MaterialTextView settings = new MaterialTextView(sharedData.getContext); //Android 5 can't show ⚙, instead show other icon instead
        settings.setText(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? "⚙" : "\uD83D\uDD27");
        settings.setTextColor(sharedData.MenuStyle.getTextColor());
        settings.setTypeface(Typeface.DEFAULT_BOLD);
        settings.setTextSize(20.0f);
        RelativeLayout.LayoutParams rlsettings = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        rlsettings.addRule(ALIGN_PARENT_RIGHT);
        settings.setLayoutParams(rlsettings);
        settings.setOnClickListener(new View.OnClickListener() {
            boolean settingsOpen;

            @Override
            public void onClick(View v) {
                try {
                    settingsOpen = !settingsOpen;
                    if (settingsOpen) {
                        sharedData.scrollView.removeView(sharedData.mods);
                        sharedData.scrollView.addView(sharedData.mSettings);
                        sharedData.scrollView.scrollTo(0, 0);
                    } else {
                        sharedData.scrollView.removeView(sharedData.mSettings);
                        sharedData.scrollView.addView(sharedData.mods);
                    }
                } catch (IllegalStateException e) {
                }
            }

        });

        return  settings;
    }


    MaterialTextView buildTitle() {
        MaterialTextView title = new MaterialTextView(sharedData.getContext);
        title.setTextColor(sharedData.MenuStyle.getTextColor());
        title.setTextSize(18.0f);
        title.setGravity(Gravity.CENTER);
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        rl.addRule(RelativeLayout.CENTER_HORIZONTAL);
        title.setLayoutParams(rl);
        return  title;
    }



    MaterialTextView buildSubTitle() {
        //********** Sub title **********
        MaterialTextView subTitle = new MaterialTextView(sharedData.getContext);
        subTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        subTitle.setMarqueeRepeatLimit(-1);
        subTitle.setSingleLine(true);
        subTitle.setSelected(true);
        subTitle.setTextColor(sharedData.MenuStyle.getTextColor());
        subTitle.setTextSize(10.0f);
        subTitle.setGravity(Gravity.CENTER);
        subTitle.setPadding(0, 0, 0, 5);
        return  subTitle;
    }

    MaterialButton buildHideButton() {
        RelativeLayout.LayoutParams lParamsHideBtn = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        lParamsHideBtn.addRule(ALIGN_PARENT_LEFT);

        MaterialButton hideBtn = new MaterialButton(sharedData.getContext);
        hideBtn.setLayoutParams(lParamsHideBtn);
        hideBtn.setBackgroundColor(Color.TRANSPARENT);
        hideBtn.setText("HIDE/KILL (Hold)");
        hideBtn.setTextColor(sharedData.MenuStyle.getTextColor());
        hideBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sharedData.mCollapsed.setVisibility(View.VISIBLE);
                sharedData.mCollapsed.setAlpha(0);
                sharedData.mExpanded.setVisibility(View.GONE);
                Toast.makeText(view.getContext(), "Icon hidden. Remember the hidden icon position", Toast.LENGTH_LONG).show();
            }
        });
        hideBtn.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                Toast.makeText(view.getContext(), "Menu killed", Toast.LENGTH_LONG).show();
                sharedData.rootFrame.removeView(sharedData.mRootContainer);
                sharedData.mWindowManager.removeView(sharedData.rootFrame);
                return false;
            }
        });
        return hideBtn;
    }

    MaterialButton buildCloseButton() {
        //********** Close button **********
        RelativeLayout.LayoutParams lParamsCloseBtn = new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        lParamsCloseBtn.addRule(ALIGN_PARENT_RIGHT);

        MaterialButton closeBtn = new MaterialButton(sharedData.getContext);
        closeBtn.setLayoutParams(lParamsCloseBtn);
        closeBtn.setBackgroundColor(Color.TRANSPARENT);
        closeBtn.setText("MINIMIZE");
        closeBtn.setTextColor(sharedData.MenuStyle.getTextColor());
        closeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sharedData.mCollapsed.setVisibility(View.VISIBLE);
                sharedData.mCollapsed.setAlpha(sharedData.MenuStyle.getIconAlpha());
                sharedData.mExpanded.setVisibility(View.GONE);
            }
        });
        return closeBtn;
    }

    /**
     *
     */
    @Override
    public void show() {
        sharedData.rootFrame.addView(sharedData.mRootContainer);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            boolean viewLoaded = false;

            @Override
            public void run() {
                if (Preferences.loadPref && !sharedData.MenuData.IsGameLibLoaded() && !sharedData.stopChecking) {
                    if (!viewLoaded) {
                        sharedData.components.Category(sharedData.mods, "Save preferences was been enabled. Waiting for game lib to be loaded...\n\nForce load menu may not apply mods instantly. You would need to reactivate them again");
                        sharedData.components.Button(sharedData.mods, -100, "Force load menu");
                        viewLoaded = true;
                    }
                    handler.postDelayed(this, 600);
                } else {
                   sharedData.mods.removeAllViews();
                   buildFeature(sharedData.MenuData.GetFeatureList(), sharedData.mods);
                }
            }
        }, 500);
    }

    /**
     * @param listFT
     * @param linearLayout
     */
    @Override
    public void buildFeature(String[] listFT, LinearLayout linearLayout) {
        //Currently looks messy right now. Let me know if you have improvements
        int featNum, subFeat = 0;
        LinearLayout llBak = linearLayout;

        for (int i = 0; i < listFT.length; i++) {
            boolean switchedOn = false;
            //Log.i("featureList", listFT[i]);
            String feature = listFT[i];
            if (feature.contains("_True")) {
                switchedOn = true;
                feature = feature.replaceFirst("_True", "");
            }

            linearLayout = llBak;
            if (feature.contains("CollapseAdd_")) {
                //if (collapse != null)
                linearLayout = sharedData.mCollapse;
                feature = feature.replaceFirst("CollapseAdd_", "");
            }
            String[] str = feature.split("_");

            //Assign feature number
            if (TextUtils.isDigitsOnly(str[0]) || str[0].matches("-[0-9]*")) {
                featNum = Integer.parseInt(str[0]);
                feature = feature.replaceFirst(str[0] + "_", "");
                subFeat++;
            } else {
                //Subtract feature number. We don't want to count ButtonLink, Category, RichTextView and RichWebView
                featNum = i - subFeat;
            }
            String[] strSplit = feature.split("_");
            switch (strSplit[0]) {
                case "Toggle":
                    sharedData.components.Switch(linearLayout, featNum, strSplit[1], switchedOn);
                    break;
                case "SeekBar":
                    sharedData.components.SeekBar(linearLayout, featNum, strSplit[1], Integer.parseInt(strSplit[2]), Integer.parseInt(strSplit[3]));
                    break;
                case "Button":
                    sharedData.components.Button(linearLayout, featNum, strSplit[1]);
                    break;
                case "ButtonOnOff":
                    sharedData.components.ButtonOnOff(linearLayout, featNum, strSplit[1], switchedOn);
                    break;
                case "Spinner":
                    sharedData.components.TextView(linearLayout, strSplit[1]);
                    sharedData.components.Spinner(linearLayout, featNum, strSplit[1], strSplit[2]);
                    break;
                case "InputText":
                    sharedData.components.InputText(linearLayout, featNum, strSplit[1]);
                    break;
                case "InputValue":
                    if (strSplit.length == 3)
                        sharedData.components.InputNum(linearLayout, featNum, strSplit[2], Integer.parseInt(strSplit[1]));
                    if (strSplit.length == 2)
                        sharedData.components.InputNum(linearLayout, featNum, strSplit[1], 0);
                    break;
                case "InputLValue":
                    if (strSplit.length == 3)
                        sharedData.components.InputLNum(linearLayout, featNum, strSplit[2], Long.parseLong(strSplit[1]));
                    if (strSplit.length == 2)
                        sharedData.components.InputLNum(linearLayout, featNum, strSplit[1], 0);
                    break;
                case "CheckBox":
                    sharedData.components.CheckBox(linearLayout, featNum, strSplit[1], switchedOn);
                    break;
                case "RadioButton":
                    sharedData.components.RadioButton(linearLayout, featNum, strSplit[1], strSplit[2]);
                    break;
                case "Collapse":
                    sharedData.components.Collapse(linearLayout, strSplit[1], switchedOn);
                    subFeat++;
                    break;
                case "ButtonLink":
                    subFeat++;
                    sharedData.components.ButtonLink(linearLayout, strSplit[1], strSplit[2]);
                    break;
                case "Category":
                    subFeat++;
                    sharedData.components.Category(linearLayout, strSplit[1]);
                    break;
                case "RichTextView":
                    subFeat++;
                    sharedData.components.TextView(linearLayout, strSplit[1]);
                    break;
                case "RichWebView":
                    subFeat++;
                    sharedData.components.WebTextView(linearLayout, strSplit[1]);
                    break;
            }
        }
    }

    /**
     *
     */
    @Override
    public void destroy() {
        if (sharedData.rootFrame != null) {
            sharedData.mWindowManager.removeView(sharedData.rootFrame);
        }
    }

    /**
     * @param visibility
     */
    @Override
    public void setVisibility(int visibility) {
        if (sharedData.rootFrame != null) {
            sharedData.rootFrame.setVisibility(visibility);
        }
    }

    private int dp(int i) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) i, sharedData.getContext.getResources().getDisplayMetrics());
    }

    private int convertDipToPixels(int i) {
        return (int) ((((float) i) * sharedData.getContext.getResources().getDisplayMetrics().density) + 0.5f);
    }



}

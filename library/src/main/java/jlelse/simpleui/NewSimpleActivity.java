/*
 *    Copyright 2015 Jan-Lukas Else
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package jlelse.simpleui;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * New try to implement the SimpleActivity better. Will be renamed to SimpleActivity later and will replace old one.
 */

public class NewSimpleActivity extends AppCompatActivity implements View.OnClickListener {


    //Fab
    private boolean fabEnabled = false;
    private Drawable fabDrawable = null;
    private int fabColor = getResources().getColor(R.color.default_accent);
    private FloatingActionButton floatingActionButton;


    //Toolbar
    private boolean toolbarEnabled = true;
    private int toolbarColor = getResources().getColor(R.color.default_toolbar);
    private Toolbar toolbar;

    //Colors
    private int primaryColor = getResources().getColor(R.color.default_primary);
    private int primaryDarkColor = getResources().getColor(R.color.default_primary_dark);
    private int accentColor = getResources().getColor(R.color.default_accent);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        enableDefaultTheme();

        setContentView(R.layout.main);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    public void onClick(View v) {

    }


    // Other methods

    //Fab

    /**
     * @param enabled If true the FloatingActionButton will be enabled. Otherwise it will be disabled.
     */
    public void enableFab(boolean enabled) {
        if (enabled) {
            floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(fabColor));
            floatingActionButton.setImageDrawable(fabDrawable);
            floatingActionButton.setVisibility(View.VISIBLE);
        } else {
            floatingActionButton.setVisibility(View.GONE);
        }
    }

    /**
     * @param drawable The Drawable for displaying on the FloatingActionButton
     */
    public void setFabDrawable(Drawable drawable) {
        fabDrawable = drawable;
        enableFab(fabEnabled);
    }

    /**
     * @param color The color in which the FloatingActionButton should appear
     */
    public void setFabColor(int color) {
        fabColor = color;
        enableFab(fabEnabled);
    }

    /**
     * @return Returns the FloatingActionButton to enable more customization etc.
     */
    public FloatingActionButton getFab() {
        return floatingActionButton;
    }

    //Toolbar

    /**
     * @param enabled If true the Toolbar will be enabled. Otherwise it will be disabled.
     */
    public void enableToolbar(boolean enabled) {
        if (enabled) {
            toolbar.setBackgroundColor(toolbarColor);
            toolbar.setVisibility(View.VISIBLE);
        } else {
            toolbar.setVisibility(View.GONE);
        }
    }

    /**
     * @param color The color in which the Toolbar should appear
     */
    public void setToolbarColor(int color) {
        toolbarColor = color;
        enableToolbar(toolbarEnabled);
    }

    /**
     * @return Returns the Toolbar to enable more customization etc.
     */
    public Toolbar getToolbar() {
        return toolbar;
    }


    //Drawer

    /**
     * @param enabled If true the NavigationDrawer will be enabled. Otherwise it will be disabled.
     */
    public void enableDrawer(boolean enabled) {
        if (enabled) {

        } else {

        }
    }

    //Theme

    /**
     * Enables the default SimpleUI theme
     */
    public void enableDefaultTheme() {
        setTheme(R.style.Theme_SimpleUI);
    }

    /**
     * Enables a custom theme
     *
     * @param resId ID of style resource
     */
    public void enableCustomThemme(int resId) {
        setTheme(resId);
    }

    // Colors

    /**
     * Sets the primary color
     *
     * @param color       The color which should be used as primary color
     * @param alsoToolbar If true the color will be used for the Toolbar too
     */
    public void setPrimaryColor(int color, boolean alsoToolbar) {
        primaryColor = color;
        if (alsoToolbar) {
            toolbarColor = color;
            enableToolbar(toolbarEnabled);
        }
        applyColorsToTheme();
    }

    /**
     * Sets the primary_dark color
     *
     * @param color The color which should be used as primary_dark color
     */
    public void setPrimaryDarkColor(int color) {
        primaryDarkColor = color;
        applyColorsToTheme();
    }

    /**
     * Sets the accent color
     *
     * @param color   The color which should be used as accent color
     * @param alsoFab If true this color will be used for the FloatingActionButton too
     */
    public void setAccentColor(int color, boolean alsoFab) {
        accentColor = color;
        if (alsoFab) {
            fabColor = color;
            enableFab(fabEnabled);
        }
        applyColorsToTheme();
    }

    private void applyColorsToTheme() {

    }

}

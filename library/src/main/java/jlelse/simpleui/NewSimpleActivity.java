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
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

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

    //Drawer
    private DrawerLayout drawerLayout = null;

    //Other
    private LinearLayout mainLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        enableDefaultTheme();

        // Important because own methods override it
        super.setContentView(R.layout.main);

        initViews();
    }

    private void initViews() {
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mainLayout = (LinearLayout) findViewById(R.id.main);

        setSupportActionBar(toolbar);
    }

    private void changeLayout(int resId) {
        List<View> views = new ArrayList<>();
        for (int i = 0; i < mainLayout.getChildCount(); i++) {
            views.add(mainLayout.getChildAt(i));
        }
        super.setContentView(resId);
        initViews();
        for (View view : views) {
            mainLayout.addView(view);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }

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
            changeLayout(R.layout.with_drawer);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    invalidateOptionsMenu();
                    syncState();
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                    invalidateOptionsMenu();
                    syncState();
                }
            };
            drawerLayout.setDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle.syncState();
        } else {
            changeLayout(R.layout.main);
            drawerLayout = null;
        }
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    //Theme

    /**
     * Enables the default SimpleUI theme
     */
    public void enableDefaultTheme() {
        setTheme(R.style.Theme_SimpleUI);
    }

    /**
     * Enables a custom theme and applies possibility to use own colors etc.
     * Please use as theme parent the themes provided by this library!
     *
     * @param resId ID of style resource
     */
    public void enableCustomThemme(int resId) {
        setTheme(resId);
    }

    // Content

    //own method
    private void addContent(View view, boolean override) {
        if (override) {
            mainLayout.removeAllViews();
        }
        mainLayout.addView(view);
    }

    private void addContent(View view, ViewGroup.LayoutParams layoutParams, boolean override) {
        if (override) {
            mainLayout.removeAllViews();
        }
        mainLayout.addView(view, layoutParams);
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = getLayoutInflater().inflate(layoutResID, null);
        addContent(view, true);
    }

    @Override
    public void setContentView(View view) {
        addContent(view, true);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        addContent(view, params, true);
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        addContent(view, params, false);
    }
}

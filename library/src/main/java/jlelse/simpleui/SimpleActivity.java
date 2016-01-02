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

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.afollestad.appthemeengine.ATE;
import com.afollestad.appthemeengine.ATEActivity;
import com.afollestad.appthemeengine.Config;

public class SimpleActivity extends ATEActivity implements View.OnClickListener {

    public static String default_theme_key = "default";

    public static int NO_DRAWER_MENU = 111111;
    //Fab
    private boolean fabEnabled = false;
    private Drawable fabDrawable = null;
    private View.OnClickListener fabListener = null;
    private FloatingActionButton floatingActionButton;
    //Toolbar
    private boolean toolbarEnabled = false;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    //Drawer
    private boolean drawerEnabled = false;
    private int drawerMenuResId = NO_DRAWER_MENU;
    private NavigationView.OnNavigationItemSelectedListener drawerListener;
    private View drawerHeaderView;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    //Theming
    private String themeKey;
    //Other
    private RelativeLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!getThemeConfig().isConfigured()) {
            int primaryColor, accentColor;
            TypedValue typedValue = new TypedValue();
            getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
            primaryColor = typedValue.data;
            typedValue = new TypedValue();
            getTheme().resolveAttribute(R.attr.colorAccent, typedValue, true);
            accentColor = typedValue.data;
            ATE.config(this, getThemeKey())
                    .primaryColor(primaryColor)
                    .autoGeneratePrimaryDark(true)
                    .accentColor(accentColor)
                    .usingMaterialDialogs(true)
                    .commit();
        }

        super.onCreate(savedInstanceState);

        // Important because own methods override it
        super.setContentView(R.layout.main);

        init();
    }

    public void init() {
        this.floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        try {
            setSupportActionBar(getToolbar());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.navigationView = (NavigationView) findViewById(R.id.navigation_view);
        this.mainLayout = (RelativeLayout) findViewById(R.id.main);
        this.mainLayout.removeAllViews();

        //Init all things
        initFab(isFabEnabled(), getFabDrawable(), getFabListener());
        initToolbar(isToolbarEnabled());
        initDrawer(isDrawerEnabled(), getDrawerMenuResId(), getDrawerListener(), getDrawerHeaderView());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }

    //Fab
    public void initFab(boolean fabEnabled, Drawable fabDrawable, View.OnClickListener fabListener) {
        this.fabEnabled = fabEnabled;
        this.fabDrawable = fabDrawable;
        this.fabListener = fabListener;

        if (isFabEnabled()) {
            getFloatingActionButton().setVisibility(View.VISIBLE);
        } else {
            getFloatingActionButton().setVisibility(View.GONE);
        }
        if (getFabDrawable() != null) {
            getFloatingActionButton().setImageDrawable(getFabDrawable());
        }
        if (getFabListener() != null) {
            getFloatingActionButton().setOnClickListener(getFabListener());
        }
    }

    public boolean isFabEnabled() {
        return fabEnabled;
    }

    public void setFabEnabled(boolean fabEnabled) {
        this.fabEnabled = fabEnabled;
        initFab(isFabEnabled(), getFabDrawable(), getFabListener());
    }

    public Drawable getFabDrawable() {
        return fabDrawable;
    }

    public void setFabDrawable(Drawable fabDrawable) {
        this.fabDrawable = fabDrawable;
        initFab(isFabEnabled(), getFabDrawable(), getFabListener());
    }

    public View.OnClickListener getFabListener() {
        return fabListener;
    }

    public void setFabListener(View.OnClickListener fabListener) {
        this.fabListener = fabListener;
        initFab(isFabEnabled(), getFabDrawable(), getFabListener());
    }

    public FloatingActionButton getFloatingActionButton() {
        return floatingActionButton;
    }

    //Toolbar
    public void initToolbar(boolean toolbarEnabled) {
        this.toolbarEnabled = toolbarEnabled;

        if (isToolbarEnabled()) {
            getToolbar().setVisibility(View.VISIBLE);
            if (!isTaskRoot()) {
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
        } else {
            getToolbar().setVisibility(View.GONE);
        }
    }

    public boolean isToolbarEnabled() {
        return toolbarEnabled;
    }

    public void setToolbarEnabled(boolean toolbarEnabled) {
        this.toolbarEnabled = toolbarEnabled;
        initToolbar(isToolbarEnabled());
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public AppBarLayout getAppBarLayout() {
        return appBarLayout;
    }

    //Drawer
    public void initDrawer(boolean drawerEnabled, int drawerMenuResId, NavigationView.OnNavigationItemSelectedListener drawerListener, View drawerHeaderView) {
        this.drawerEnabled = drawerEnabled;
        this.drawerMenuResId = drawerMenuResId;
        this.drawerListener = drawerListener;
        this.drawerHeaderView = drawerHeaderView;

        ActionBar actionBar = getSupportActionBar();

        if (isDrawerEnabled()) {
            getDrawerLayout().setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeButtonEnabled(true);
                ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, getDrawerLayout(), getToolbar(), R.string.drawer_open, R.string.drawer_close) {
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
                getDrawerLayout().setDrawerListener(actionBarDrawerToggle);
                actionBarDrawerToggle.syncState();
            }
            getNavigationView().getMenu().clear();
            if (getDrawerMenuResId() != NO_DRAWER_MENU) {
                getNavigationView().inflateMenu(getDrawerMenuResId());
            }
            if (getDrawerListener() != null) {
                getNavigationView().setNavigationItemSelectedListener(getDrawerListener());
            } else {
                getNavigationView().setNavigationItemSelectedListener(null);
            }
            try {
                getNavigationView().removeHeaderView(getDrawerHeaderView());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (getDrawerHeaderView() != null) {
                getNavigationView().addHeaderView(getDrawerHeaderView());
            }
        } else {
            getDrawerLayout().setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(false);
                actionBar.setHomeButtonEnabled(false);
            }
        }


    }

    public boolean isDrawerEnabled() {
        return drawerEnabled;
    }

    public void setDrawerEnabled(boolean drawerEnabled) {
        this.drawerEnabled = drawerEnabled;
        initDrawer(isDrawerEnabled(), getDrawerMenuResId(), getDrawerListener(), getDrawerHeaderView());
    }

    public int getDrawerMenuResId() {
        return drawerMenuResId;
    }

    public void setDrawerMenuResId(int drawerMenuResId) {
        this.drawerMenuResId = drawerMenuResId;
        initDrawer(isDrawerEnabled(), getDrawerMenuResId(), getDrawerListener(), getDrawerHeaderView());
    }

    public NavigationView.OnNavigationItemSelectedListener getDrawerListener() {
        return drawerListener;
    }

    public void setDrawerListener(NavigationView.OnNavigationItemSelectedListener drawerListener) {
        this.drawerListener = drawerListener;
        initDrawer(isDrawerEnabled(), getDrawerMenuResId(), getDrawerListener(), getDrawerHeaderView());
    }

    public View getDrawerHeaderView() {
        return drawerHeaderView;
    }

    public void setDrawerHeaderView(View drawerHeaderView) {
        this.drawerHeaderView = drawerHeaderView;
        initDrawer(isDrawerEnabled(), getDrawerMenuResId(), getDrawerListener(), getDrawerHeaderView());
    }

    public NavigationView getNavigationView() {
        return navigationView;
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
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

    //Theming methods
    public String getThemeKey() {
        if (themeKey != null) {
            return themeKey;
        } else {
            return default_theme_key;
        }
    }

    public Config getThemeConfig() {
        return ATE.config(this, getThemeKey());
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

    @Override
    public void onBackPressed() {
        if (isDrawerEnabled() && getDrawerLayout().isDrawerOpen(GravityCompat.START)) {
            getDrawerLayout().closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

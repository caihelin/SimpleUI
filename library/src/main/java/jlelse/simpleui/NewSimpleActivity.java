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
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * New try to implement the SimpleActivity better. Will be renamed to SimpleActivity later and will replace old one.
 */

public class NewSimpleActivity extends AppCompatActivity implements View.OnClickListener {

    //Fab
    private boolean fabEnabled = false;
    private Drawable fabDrawable = null;
    private int fabColor = DEFAULT_FAB_COLOR;
    private View.OnClickListener fabListener = null;
    private FloatingActionButton floatingActionButton;

    public static int DEFAULT_FAB_COLOR = 111111;

    //Toolbar
    private boolean toolbarEnabled = false;
    private int toolbarColor = DEFAULT_TOOLBAR_COLOR;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;

    public static int DEFAULT_TOOLBAR_COLOR = 111111;

    //Drawer
    private boolean drawerEnabled = false;
    private int drawerMenuResId = NO_DRAWER_MENU;
    private NavigationView.OnNavigationItemSelectedListener drawerListener;
    private View drawerHeaderView;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    public static int NO_DRAWER_MENU = 111111;

    //Other
    private LinearLayout mainLayout;
    private int colorPrimaryDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        enableDefaultTheme();

        // Important because own methods override it
        super.setContentView(R.layout.with_drawer);

        init();
    }

    public void init() {
        this.floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(getToolbar());
        this.appBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.navigationView = (NavigationView) findViewById(R.id.navigation_view);
        this.mainLayout = (LinearLayout) findViewById(R.id.main);
        this.mainLayout.removeAllViews();

        //Init all things
        initFab(isFabEnabled(), getFabDrawable(), getFabColor(), getFabListener());
        initToolbar(isToolbarEnabled(), getToolbarColor());
        initDrawer(isDrawerEnabled(), getDrawerMenuResId(), getDrawerListener(), getDrawerHeaderView());
        
        //Color statusbar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && colorPrimaryDark != 0) {
            Window window = getWindow();
            window.setStatusBarColor(getResources().getColor(android.R.color.transparent));
            drawerLayout.setStatusBarBackgroundColor(colorPrimaryDark);
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
    public void initFab(boolean fabEnabled, Drawable fabDrawable, int fabColor, View.OnClickListener fabListener) {
        this.fabEnabled = fabEnabled;
        this.fabDrawable = fabDrawable;
        this.fabColor = fabColor;
        this.fabListener = fabListener;

        if (isFabEnabled()) {
            getFloatingActionButton().setVisibility(View.VISIBLE);
        } else {
            getFloatingActionButton().setVisibility(View.GONE);
        }
        if (getFabDrawable() != null) {
            getFloatingActionButton().setImageDrawable(getFabDrawable());
        }
        if (getFabColor() != DEFAULT_FAB_COLOR) {
            getFloatingActionButton().setBackgroundTintList(ColorStateList.valueOf(getFabColor()));
        }
        if (getFabListener() != null) {
            getFloatingActionButton().setOnClickListener(getFabListener());
        }
    }

    public boolean isFabEnabled() {
        return fabEnabled;
    }

    public Drawable getFabDrawable() {
        return fabDrawable;
    }

    public int getFabColor() {
        return fabColor;
    }

    public View.OnClickListener getFabListener() {
        return fabListener;
    }

    public FloatingActionButton getFloatingActionButton() {
        return floatingActionButton;
    }

    public void setFabEnabled(boolean fabEnabled) {
        this.fabEnabled = fabEnabled;
        initFab(isFabEnabled(), getFabDrawable(), getFabColor(), getFabListener());
    }

    public void setFabDrawable(Drawable fabDrawable) {
        this.fabDrawable = fabDrawable;
        initFab(isFabEnabled(), getFabDrawable(), getFabColor(), getFabListener());
    }

    public void setFabColor(int fabColor) {
        this.fabColor = fabColor;
        initFab(isFabEnabled(), getFabDrawable(), getFabColor(), getFabListener());
    }

    public void setFabListener(View.OnClickListener fabListener) {
        this.fabListener = fabListener;
        initFab(isFabEnabled(), getFabDrawable(), getFabColor(), getFabListener());
    }

    //Toolbar
    public void initToolbar(boolean toolbarEnabled, int toolbarColor) {
        this.toolbarEnabled = toolbarEnabled;
        this.toolbarColor = toolbarColor;

        if (isToolbarEnabled()) {
            getToolbar().setVisibility(View.VISIBLE);
        } else {
            getToolbar().setVisibility(View.GONE);
        }
        if (getToolbarColor() != DEFAULT_TOOLBAR_COLOR) {
            getAppBarLayout().setBackgroundColor(getToolbarColor());
        }
    }

    public boolean isToolbarEnabled() {
        return toolbarEnabled;
    }

    public int getToolbarColor() {
        return toolbarColor;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public AppBarLayout getAppBarLayout() {
        return appBarLayout;
    }

    public void setToolbarEnabled(boolean toolbarEnabled) {
        this.toolbarEnabled = toolbarEnabled;
        initToolbar(isToolbarEnabled(), getToolbarColor());
    }

    public void setToolbarColor(int toolbarColor) {
        this.toolbarColor = toolbarColor;
        initToolbar(isToolbarEnabled(), getToolbarColor());
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
            }
            if (getDrawerMenuResId() != NO_DRAWER_MENU) {
                getNavigationView().inflateMenu(getDrawerMenuResId());
            } else {
                getNavigationView().getMenu().clear();
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
            }
        }

        // DrawerToggle
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

    public boolean isDrawerEnabled() {
        return drawerEnabled;
    }

    public int getDrawerMenuResId() {
        return drawerMenuResId;
    }

    public NavigationView.OnNavigationItemSelectedListener getDrawerListener() {
        return drawerListener;
    }

    public View getDrawerHeaderView() {
        return drawerHeaderView;
    }

    public NavigationView getNavigationView() {
        return navigationView;
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public void setDrawerEnabled(boolean drawerEnabled) {
        this.drawerEnabled = drawerEnabled;
        initDrawer(isDrawerEnabled(), getDrawerMenuResId(), getDrawerListener(), getDrawerHeaderView());
    }

    public void setDrawerMenuResId(int drawerMenuResId) {
        this.drawerMenuResId = drawerMenuResId;
        initDrawer(isDrawerEnabled(), getDrawerMenuResId(), getDrawerListener(), getDrawerHeaderView());
    }

    public void setDrawerListener(NavigationView.OnNavigationItemSelectedListener drawerListener) {
        this.drawerListener = drawerListener;
        initDrawer(isDrawerEnabled(), getDrawerMenuResId(), getDrawerListener(), getDrawerHeaderView());
    }

    public void setDrawerHeaderView(View drawerHeaderView) {
        this.drawerHeaderView = drawerHeaderView;
        initDrawer(isDrawerEnabled(), getDrawerMenuResId(), getDrawerListener(), getDrawerHeaderView());
    }

    //Theme

    /**
     * Enables the default SimpleUI theme
     */
    public void enableDefaultTheme() {
        enableCustomTheme(R.style.Theme_SimpleUI);
    }

    /**
     * Enables a custom theme and applies possibility to use own colors etc.
     * Please use as theme parent the themes provided by this library!
     *
     * @param resId ID of style resource
     */
    public void enableCustomTheme(int resId) {
        setTheme(resId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            TypedValue typedValue = new TypedValue();
            getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
            colorPrimaryDark = typedValue.data;
        }
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

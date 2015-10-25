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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public abstract class SimpleActivity extends AppCompatActivity {

    private LinearLayout main;
    private FloatingActionButton floatingActionButton;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;

    public abstract boolean fab();

    public abstract Drawable fabDrawable();

    public abstract View.OnClickListener fabOnClickListener();

    public abstract boolean drawer();

    public abstract int drawerMenuRes();

    public abstract NavigationView.OnNavigationItemSelectedListener drawerMenuListener();

    public abstract boolean optionsMenu();

    public abstract int optionsMenuRes();

    public abstract boolean toolbar();

    public abstract int primaryColor();

    public abstract int primaryColorDark();

    public abstract int accentColor();

    public abstract void init();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.Theme_SimpleUI);

        //Init Drawer
        if (drawer()) {
            setContentView(R.layout.with_drawer);
            navigationView = (NavigationView) findViewById(R.id.navigation_view);
            if (drawerMenuRes() != 0) setDrawerMenu(drawerMenuRes());
            if (drawerMenuListener() != null) setDrawerMenuListener(drawerMenuListener());
        } else {
            setContentView(R.layout.main);
        }

        //Init Toolbar etc.
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setPopupTheme(R.style.ThemeOverlay_SimpleUI);
        setSupportActionBar(toolbar);

        //Finish Drawer Init
        if (drawer()) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
            DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        }

        //Init Fab
        if (fab()) {
            floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
            if (accentColor() != 0) setFabColor(accentColor());
            if (fabDrawable() != null) setFabDrawable(fabDrawable());
            if (fabOnClickListener() != null) setFabOnClickListener(fabOnClickListener());
        } else {
            floatingActionButton.setVisibility(View.GONE);
        }


        //Init Toolbar & AppBarLayout
        if (!toolbar()) toolbar.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (primaryColorDark() != 0) window.setStatusBarColor(primaryColorDark());
        }
        if (primaryColor() != 0) appBarLayout.setBackgroundColor(primaryColor());

        main = (LinearLayout) findViewById(R.id.main);

        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(optionsMenu()){
            getMenuInflater().inflate(optionsMenuRes(), menu);
        }
        return true;
    }

    //Manual available customizations
    public void setContent(View view) {
        main.removeAllViews();
        main.addView(view);
    }

    public void addView(View view) {
        main.addView(view);
    }

    public void setFabColor(int color) {
        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(color));
    }

    public void setFabDrawable(Drawable drawable) {
        floatingActionButton.setImageDrawable(drawable);
    }

    public void setFabOnClickListener(View.OnClickListener onClickListener) {
        floatingActionButton.setOnClickListener(onClickListener);
    }

    public void setDrawerMenu(int menuRes) {
        new MenuInflater(this).inflate(menuRes, getDrawerMenu());
    }

    public void setDrawerMenuListener(NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener) {
        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    //Enable deeper access to special components
    public Menu getDrawerMenu() {
        return navigationView.getMenu();
    }

    public AppBarLayout getAppBarLayout() {
        return appBarLayout;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }
}

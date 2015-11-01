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

package jlelse.simpleuisample;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import jlelse.simpleui.SimpleActivity;
import jlelse.simpleui.SimpleDialog;

public class SampleActivity extends SimpleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // OPTIONAL
        enableCustomTheme(R.style.Theme1);

        // FAB
        // This way ...
        setFabColor(getResources().getColor(R.color.accent));
        setFabDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        setFabListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDrawerEnabled(!isDrawerEnabled());
            }
        });
        setFabEnabled(true);
        // ... or this
        initFab(true, getResources().getDrawable(R.mipmap.ic_launcher), getResources().getColor(R.color.accent), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDrawerEnabled(!isDrawerEnabled());
            }
        });

        // Toolbar
        // This way ...
        setToolbarColor(getResources().getColor(R.color.primary));
        setToolbarEnabled(true);
        // ... or this
        initToolbar(true, getResources().getColor(R.color.primary));

        // Drawer
        // This way ...
        setDrawerMenuResId(R.menu.drawer);
        setDrawerListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                new SimpleDialog(SampleActivity.this).SimpleOKDialog("Test", "Hey!");
                getDrawerLayout().closeDrawers();
                return true;
            }
        });
        TextView headerView = new TextView(this);
        headerView.setText("Hi!");
        setDrawerHeaderView(headerView);
        setDrawerEnabled(true);
        // ... or this
        initDrawer(true, R.menu.drawer, new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                new SimpleDialog(SampleActivity.this).SimpleOKDialog("Test", "Hey!");
                getDrawerLayout().closeDrawers();
                return true;
            }
        }, headerView);
    }
}

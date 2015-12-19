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
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import jlelse.simpleui.SimpleActivity;
import jlelse.simpleui.SimpleDialog;

public class SampleActivity extends SimpleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // FAB
        // This way ...
        setFabColor(ContextCompat.getColor(this, R.color.SimpleColorAccent));
        setFabDrawable(ContextCompat.getDrawable(this, R.mipmap.ic_launcher));
        setFabListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDrawerEnabled(!isDrawerEnabled());
            }
        });
        setFabEnabled(true);
        // ... or this
        initFab(true, ContextCompat.getDrawable(this, R.mipmap.ic_launcher), ContextCompat.getColor(this, R.color.SimpleColorAccent), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDrawerEnabled(!isDrawerEnabled());
            }
        });

        // Toolbar
        // This way ...
        setToolbarColor(ContextCompat.getColor(this, R.color.SimpleColorPrimary));
        setToolbarEnabled(true);
        // ... or this
        initToolbar(true, ContextCompat.getColor(this, R.color.SimpleColorPrimary));

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

        NestedScrollView contentSV = new NestedScrollView(this);
        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        contentSV.addView(content);
        setContentView(contentSV, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        for (int i = 0; i < 100; i++) {
            TextView sampleTV = new TextView(this);
            sampleTV.setText(R.string.app_name);
            content.addView(sampleTV);
        }
    }
}

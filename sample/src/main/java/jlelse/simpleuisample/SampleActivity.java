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

import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import jlelse.simpleui.SimpleActivity;
import jlelse.simpleui.SimpleDialog;

public class SampleActivity extends SimpleActivity {

    @Override
    public boolean fab() {
        return true;
    }

    @Override
    public Drawable fabDrawable() {
        return getResources().getDrawable(R.mipmap.ic_launcher);
    }

    @Override
    public View.OnClickListener fabOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SimpleDialog(SampleActivity.this).SimpleOKDialog("Hi!", "Lol...");
            }
        };
    }

    @Override
    public boolean drawer() {
        return false;
    }

    @Override
    public int drawerMenuRes() {
        return R.menu.drawer;
    }

    @Override
    public NavigationView.OnNavigationItemSelectedListener drawerMenuListener() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                new SimpleDialog(SampleActivity.this).SimpleAlertDialog("Item", String.valueOf(item.getTitle()));
                return true;
            }
        };
    }

    @Override
    public boolean optionsMenu() {
        return true;
    }

    @Override
    public int optionsMenuRes() {
        return R.menu.drawer;
    }

    @Override
    public boolean toolbar() {
        return true;
    }

    @Override
    public int primaryColor() {
        return getResources().getColor(R.color.primary);
    }

    @Override
    public int primaryColorDark() {
        return getResources().getColor(R.color.primary_dark);
    }

    @Override
    public int accentColor() {
        return getResources().getColor(R.color.accent);
    }

    @Override
    public void init() {
        Button button = new Button(this);
        button.setText("Click me");
        addView(button);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

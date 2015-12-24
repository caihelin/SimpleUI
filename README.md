# SimpleUI

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-SimpleUI-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/2677)

## What is SimpleUI?

SimpleUI is an Android library which helps you creating the activity UI more easier.

## How to use SimpleUI?

### Gradle

SimpleUI is available via JitPack.

Add this to your root `build.gradle`:

```
 allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
 }
```

and this to your module `build.gradle`:

```
 dependencies {
        ...
        compile 'com.github.jlelse:SimpleUI:0.5'
    }
```

That's it!

### Usage in `Activity`

Make your Activity extending `jlelse.simpleui.SimpleActivity`

```java
import jlelse.simpleui.SimpleActivity;

public class SampleActivity extends SimpleActivity {
```

Please  use `SimpleTheme.Default` as your style / theme parent for your activity and `SimpleTheme` as style / theme parent for your application.

You can use the library this way:

```java
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
```

For more information and a sample look at the sample module provided in this repository.

## Advanced features

There is also a `SimpleDialogs` class with some useful methods to use alert dialogs more simple.

## Dependencies

SimpleUI has the following Gradle dependencies:

```
compile 'com.android.support:appcompat-v7:23.1.1'
compile 'com.android.support:design:23.1.1'
```

## Credits

**Developer:** <a href="https://github.com/jlelse">jlelse</a>

## License

SimpleUI is licensed under the Apache 2.0 license

```
   Copyright 2015 Jan-Lukas Else

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
# SimpleUI

## What is SimpleUI?

SimpleUI is an Android library which helps you creating the activity UI more easier.
You just have to configure the main things instead of coding them yourself.

## How to use SimpleUI?

### Gradle

SimpleUI is available via JitPack.

Add this in your build.gradle at the end of repositories:

```
 repositories {
        // ...
        maven { url "https://jitpack.io" }
    }
```

And add the dependency in the form

```
 dependencies {
        compile 'com.github.jlelse:SimpleUI:+'
    }
```

That's it!

### Usage in `Activity`

#### 1. Make your Activity extending `jlelse.simpleui.SimpleActivity`

```java
import jlelse.simpleui.SimpleActivity;

public class SampleActivity extends SimpleActivity {
```

#### 2. Implement all abstract methods

You can do this by using the *Implement Methods* options from Android Studios context menu.

#### 3. Add following override methods to your class

##### `onOptionsItemSelected(MenuItem item)` (Optional)

If you enabled the optionMenu, this method is the *"selection listener"*
Tip: Use `switch(item)` inside.

```java
public boolean onOptionsItemSelected(MenuItem item) {}{
    ...
}
```

## Advanced features

There is also a `SimpleDialogs` class with some useful methods to use alert dialogs more simple.

## Dependencies

SimpleUI has the following Gradle dependencies:

```
compile 'com.android.support:appcompat-v7:+'
compile 'com.android.support:design:+'
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
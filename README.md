# SimpleUI

## What is SimpleUI?

SimpleUI is an Android library which helps you creating the activity UI more easier.

## How to use SimpleUI?

### Gradle

SimpleUI is available via jCenter.

Add this to your build.gradle:

```
 dependencies {
        compile 'jlelse:SimpleUI:0.3.1'
    }
```

That's it!

### Usage in `Activity`

Make your Activity extending `jlelse.simpleui.SimpleActivity`

```java
import jlelse.simpleui.SimpleActivity;

public class SampleActivity extends SimpleActivity {
```

Please  use `Theme.SimpleUI` as your style / theme parent.

For more information and a sample of how to use see the SampleActivity. You can find it <a href="https://github.com/jlelse/SimpleUI/blob/master/sample/src/main/java/jlelse/simpleuisample/SampleActivity.java">here</a>

## Advanced features

There is also a `SimpleDialogs` class with some useful methods to use alert dialogs more simple.

## Dependencies

SimpleUI has the following Gradle dependencies:

```
compile 'com.android.support:appcompat-v7:23.1.0'
compile 'com.android.support:design:23.1.0'
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
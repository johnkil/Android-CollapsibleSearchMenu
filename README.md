Android CollapsibleSearchMenu Library
=====================================

Implementation of the SearchView is compatible with Android below 3.0. 
This library allows developers to easily integrate search menuItem in an Android application.

Sample
------

A sample application is available on Google Play:

<a href="http://play.google.com/store/apps/details?id=com.devspark.collapsiblesearchmenu.sample">
  <img alt="Get it on Google Play"
       src="http://www.android.com/images/brand/get_it_on_play_logo_small.png" />
</a>

![Example Image][1]

The source code is available in this repository.

Compatibility
-------------

This library is compatible from API 7 (Android 2.1).

Installation
------------

The library project requires:
* [ActionBarSherlock](https://github.com/JakeWharton/ActionBarSherlock)

Usage
-----

Add the SearchMenuItem to the menu.

Code for Activity:

``` java
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    searchMenuItem = CollapsibleMenuUtils.addSearchMenuItem(menu, true, textWatcher);
    // other code
    return true;
}
```

Code for Fragment:

``` java
@Override
public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    searchMenuItem = CollapsibleMenuUtils.addSearchMenuItem(menu, true, textWatcher);
    // other code
}
```

Developed By
------------
* Evgeny Shishkin - <johnkil78@gmail.com>

License
-------

    Copyright 2012 Evgeny Shishkin
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
    http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[1]: http://i50.tinypic.com/2h72yqo.jpg
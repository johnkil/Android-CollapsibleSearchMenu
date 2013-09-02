/*
 * Copyright (C) 2013 Evgeny Shishkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.devspark.collapsiblesearchmenu.sample;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.devspark.collapsiblesearchmenu.CollapsibleMenuUtils;

/**
 * @author Evgeny Shishkin
 */
public class MainActivity extends SherlockListActivity {

    String[] items = new String[]{
            "China",
            "India",
            "United States",
            "Indonesia",
            "Brazil",
            "Pakistan",
            "Nigeria",
            "Bangladesh",
            "Russia",
            "Japan",
            "Mexico",
            "Philippines",
            "Vietnam",
            "Ethiopia",
            "Egypt",
            "Germany",
            "Iran",
            "Turkey",
            "Democratic Republic of the Congo",
            "Thailand",
            "France",
            "United Kingdom",
            "Italy",
            "South Africa",
            "Myanmar",
            "South Korea",
            "Colombia",
            "Spain",
            "Ukraine",
            "Tanzania",
            "Kenya",
            "Argentina",
            "Poland",
            "Algeria",
            "Canada"
    };
    private MenuItem searchMenuItem;
    private ArrayAdapter<String> mArrayAdapter;
    private CollapsibleMenuUtils.OnQueryTextListener mOnQueryTextListener = new CollapsibleMenuUtils.OnQueryTextListener() {

        @Override
        public void onQueryTextSubmit(String query) {
        }

        @Override
        public void onQueryTextChange(String newText) {
            mArrayAdapter.getFilter().filter(newText);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        setListAdapter(mArrayAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // ATTENTION: need to do to closure of the keyboard when you click on home
        if (searchMenuItem.isActionViewExpanded()) {
            searchMenuItem.collapseActionView();
            // reset filter
            mArrayAdapter.getFilter().filter(null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        searchMenuItem = CollapsibleMenuUtils.addSearchMenuItem(menu, true, mOnQueryTextListener);
        return true;
    }

}

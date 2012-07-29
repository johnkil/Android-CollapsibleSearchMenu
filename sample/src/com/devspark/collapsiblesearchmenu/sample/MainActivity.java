package com.devspark.collapsiblesearchmenu.sample;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.devspark.collapsiblesearchmenu.CollapsibleMenuUtils;

/**
 * 
 * @author e.shishkin
 *
 */
public class MainActivity extends SherlockListActivity {
	
	private MenuItem searchMenuItem;
	private ArrayAdapter<String> mArrayAdapter;

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
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	searchMenuItem = CollapsibleMenuUtils.addSearchMenuItem(menu, true, textWatcher);
        return true;
    }
    
    private TextWatcher textWatcher = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			mArrayAdapter.getFilter().filter(s);
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
		
		@Override
		public void afterTextChanged(Editable s) {}
	};
	
	String[] items = new String[] {
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
    
}

package com.devspark.collapsiblesearchmenu;

import android.content.Context;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnActionExpandListener;

/**
 * 
 * @author e.shishkin
 *
 */
public class CollapsibleMenuUtils {
	
	/**
	 * Adding collapsible search menu item to the menu.
	 * @param menu
	 * @param isLightTheme - true if use light them for ActionBar, else false
	 * @return
	 */
	public static MenuItem addSearchMenuItem(Menu menu, boolean isLightTheme, final TextWatcher textWatcher) {
		final MenuItem menuItem = menu.add(Menu.NONE, R.id.collapsible_search_menu_item, Menu.NONE, R.string.search_go);
		menuItem.setIcon(isLightTheme ? R.drawable.ic_action_search_holo_light : R.drawable.ic_action_search_holo_dark)
	        .setActionView(isLightTheme ? R.layout.search_view_holo_light : R.layout.search_view_holo_dark)
	        .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
		
		final View searchView = menuItem.getActionView();
		final AutoCompleteTextView editText = (AutoCompleteTextView) searchView.findViewById(R.id.search_src_text);
		menuItem.setOnActionExpandListener(new OnActionExpandListener() {
			
			@Override
			public boolean onMenuItemActionExpand(MenuItem item) {
				editText.addTextChangedListener(textWatcher);
				editText.requestFocus();
				showKeyboard(editText);
				return true;
			}
			
			@Override
			public boolean onMenuItemActionCollapse(MenuItem item) {
				editText.removeTextChangedListener(textWatcher);
				editText.setText(null);
				// editText.clearFocus();
				hideKeyboard(editText);
				return true;
			}
		});
		
		final View searchPlate = searchView.findViewById(R.id.search_plate);
		editText.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, final boolean hasFocus) {
				searchView.post(new Runnable() {
					public void run() {
						searchPlate.getBackground().setState(hasFocus ? 
								new int[] {android.R.attr.state_focused} : new int[] {android.R.attr.state_empty});
						searchView.invalidate();
					}
				});
			}
		});
		
		final ImageView closeBtn = (ImageView) menuItem.getActionView().findViewById(R.id.search_close_btn);
		closeBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!TextUtils.isEmpty(editText.getText())) {
					editText.setText(null);
				} else {
					menuItem.collapseActionView();
				}
			}
		});
		
		return menuItem;
	}
	
	/**
	 * Shows the keyboard.
	 * @param view
	 */
	private static void showKeyboard(View view) {
	    Context context = view.getContext();
	    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
	    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
	}

	/**
	 * Hides the keyboard.
	 * @param view
	 */
	private static void hideKeyboard(View view) {
	    Context context = view.getContext();
	    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
	    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

}

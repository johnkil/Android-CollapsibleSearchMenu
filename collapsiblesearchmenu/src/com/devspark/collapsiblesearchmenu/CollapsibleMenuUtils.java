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

package com.devspark.collapsiblesearchmenu;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnActionExpandListener;

/**
 * @author Evgeny Shishkin
 */
public class CollapsibleMenuUtils {

    /**
     * Adding collapsible search menu item to the menu.
     *
     * @param menu
     * @param isLightTheme - true if use light them for ActionBar, else false
     * @return menu item
     */
    public static MenuItem addSearchMenuItem(Menu menu, boolean isLightTheme, final OnQueryTextListener onQueryChangeListener) {
        final MenuItem menuItem = menu.add(Menu.NONE, R.id.collapsible_search_menu_item, Menu.NONE, R.string.search_go);
        menuItem.setIcon(isLightTheme ? R.drawable.ic_action_search_holo_light : R.drawable.ic_action_search_holo_dark)
                .setActionView(isLightTheme ? R.layout.search_view_holo_light : R.layout.search_view_holo_dark)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

        final View searchView = menuItem.getActionView();
        final AutoCompleteTextView editText = (AutoCompleteTextView) searchView.findViewById(R.id.search_src_text);
        final TextWatcher textWatcher = new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                onQueryChangeListener.onQueryTextChange(charSequence.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        final TextView.OnEditorActionListener onEditorActionListener = new

                TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        if (i == EditorInfo.IME_ACTION_SEARCH ||
                                keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            onQueryChangeListener.onQueryTextSubmit(textView.getText().toString());
                            return true;
                        }
                        return false;
                    }
                };
        menuItem.setOnActionExpandListener(new OnActionExpandListener() {

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                editText.addTextChangedListener(textWatcher);
                editText.setOnEditorActionListener(onEditorActionListener);
                editText.requestFocus();
                showKeyboard(editText);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                editText.setText(null);
                editText.removeTextChangedListener(textWatcher);
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
                                new int[]{android.R.attr.state_focused} : new int[]{android.R.attr.state_empty});
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
     *
     * @param view
     */
    private static void showKeyboard(View view) {
        Context context = view.getContext();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * Hides the keyboard.
     *
     * @param view
     */
    private static void hideKeyboard(View view) {
        Context context = view.getContext();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Callbacks for changes to the query text.
     */
    public interface OnQueryTextListener {

        /**
         * Called when the user submits the query. This could be due to a key press on the
         * keyboard or due to pressing a submit button.
         * The listener can override the standard behavior by returning true
         * to indicate that it has handled the submit request. Otherwise return false to
         * let the SearchView handle the submission by launching any associated intent.
         *
         * @param query the query text that is to be submitted
         */
        void onQueryTextSubmit(String query);

        /**
         * Called when the query text is changed by the user.
         *
         * @param newText the new content of the query text field.
         */
        void onQueryTextChange(String newText);
    }

}

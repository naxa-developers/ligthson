/*
 * Copyright 2018 Nafundi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.light.collect.android.preferences;

import android.os.Bundle;

import org.light.collect.android.R;
import org.light.collect.android.activities.CollectAbstractActivity;
import org.light.collect.android.application.Collect;
import org.light.collect.android.listeners.OnBackPressedListener;
import org.light.collect.android.utilities.ThemeUtils;

public class PreferencesActivity extends CollectAbstractActivity {

    public static final String TAG = "GeneralPreferencesFragment";
    public static final String INTENT_KEY_ADMIN_MODE = "adminMode";

    private OnBackPressedListener onBackPressedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(new ThemeUtils(this).getSettingsTheme());

        setTitle(R.string.general_preferences);
        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, GeneralPreferencesFragment.newInstance(getIntent().getBooleanExtra(INTENT_KEY_ADMIN_MODE, false)), TAG)
                    .commit();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Collect.getInstance().initProperties();
    }

    // If the onBackPressedListener is set then onBackPressed is delegated to it.
    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null) {
            onBackPressedListener.doBack();
        } else {
            super.onBackPressed();
        }
    }

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }
}
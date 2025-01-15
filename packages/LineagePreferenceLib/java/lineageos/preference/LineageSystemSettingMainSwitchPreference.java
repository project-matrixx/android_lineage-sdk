/*
 * SPDX-FileCopyrightText: 2021 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package lineageos.preference;

import android.content.Context;
import android.os.UserHandle;
import android.util.AttributeSet;

import androidx.preference.PreferenceDataStore;

import com.android.settingslib.widget.MainSwitchPreference;

import lineageos.providers.LineageSettings;

public class LineageSystemSettingMainSwitchPreference extends MainSwitchPreference {

    public LineageSystemSettingMainSwitchPreference(Context context, AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
        setPreferenceDataStore(new DataStore());
    }

    public LineageSystemSettingMainSwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPreferenceDataStore(new DataStore());
    }

    public LineageSystemSettingMainSwitchPreference(Context context) {
        super(context);
        setPreferenceDataStore(new DataStore());
    }

    private class DataStore extends PreferenceDataStore {
        @Override
        public void putBoolean(String key, boolean value) {
            LineageSettings.System.putIntForUser(getContext().getContentResolver(), key, value ? 1 : 0, UserHandle.USER_CURRENT);
        }

        @Override
        public boolean getBoolean(String key, boolean defaultValue) {
            return LineageSettings.System.getIntForUser(getContext().getContentResolver(), key,
                    defaultValue ? 1 : 0, UserHandle.USER_CURRENT) != 0;
        }
    }
}

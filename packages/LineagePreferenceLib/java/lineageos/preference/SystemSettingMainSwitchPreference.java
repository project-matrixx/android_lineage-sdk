/*
 * SPDX-FileCopyrightText: 2021 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package lineageos.preference;

import android.content.Context;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.AttributeSet;

import androidx.preference.PreferenceDataStore;

import com.android.settingslib.widget.MainSwitchPreference;

public class SystemSettingMainSwitchPreference extends MainSwitchPreference {

    public SystemSettingMainSwitchPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setPreferenceDataStore(new DataStore());
    }

    public SystemSettingMainSwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPreferenceDataStore(new DataStore());
    }

    public SystemSettingMainSwitchPreference(Context context) {
        super(context, null);
        setPreferenceDataStore(new DataStore());
    }

    private class DataStore extends PreferenceDataStore {
        @Override
        public void putBoolean(String key, boolean value) {
            Settings.System.putIntForUser(getContext().getContentResolver(), key, value ? 1 : 0, UserHandle.USER_CURRENT);
        }

        @Override
        public boolean getBoolean(String key, boolean defaultValue) {
            return Settings.System.getIntForUser(getContext().getContentResolver(), key,
                    defaultValue ? 1 : 0, UserHandle.USER_CURRENT) != 0;
        }
    }
}

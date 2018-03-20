package com.chenning.movies.popularmovie;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

/**
 * Created by chenningzhang on 3/16/18.
 */

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);
        Preference.OnPreferenceChangeListener onPreferenceChangeListener = new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                Preference.OnPreferenceChangeListener listener = (Preference.OnPreferenceChangeListener) getActivity();
                listener.onPreferenceChange(preference, o);
                return true;
            }
        };
        ListPreference listPreference = (ListPreference) getPreferenceManager().findPreference(getString(R.string.pref_sort_order_key));
        listPreference.setOnPreferenceChangeListener(onPreferenceChangeListener);
    }
}

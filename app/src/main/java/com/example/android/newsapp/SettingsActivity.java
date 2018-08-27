package com.example.android.newsapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.Set;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.settings_activity);
    }

    public static class newPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
        @Override
        public void onCreate(Bundle savedInstanceSate) {
            super.onCreate(savedInstanceSate);
            addPreferencesFromResource(R.xml.settings_main);

            Preference pageSize = findPreference(getString(R.string.settings_page_size_key));
            bindPrefereneceSummaryToValue(pageSize);
        }

        private void bindPrefereneceSummaryToValue(Preference preference) {
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            if(preference instanceof ListPreference) {
                Set<String> values = sharedPrefs.getStringSet(preference.getKey(), null);
                onPreferenceChange(preference, values);
            } else {
                String preferenceString = sharedPrefs.getString(preference.getKey(), "");
                onPreferenceChange(preference, preferenceString);
            }
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();
            if(preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int prefIndex = listPreference.findIndexOfValue(stringValue);
                if(prefIndex >= 0){
                    CharSequence[] labels = listPreference.getEntries();
                    preference.setSummary(labels[prefIndex]);
                }
            } else {
                preference.setSummary(stringValue);
            }
            return true;
        }
    }
}

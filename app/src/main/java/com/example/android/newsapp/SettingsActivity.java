package com.example.android.newsapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.settings_activity);
    }

    public static class newsPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

        @Override
        public void onCreate(Bundle savedInstanceSate) {
            super.onCreate(savedInstanceSate);
            addPreferencesFromResource(R.xml.settings_main);

            Preference sortOrder = findPreference(getString(R.string.sort_order_by_key));
            bindPrefereneceSummaryToValue(sortOrder);

            Preference sectionId = findPreference(getString(R.string.category_key));
            bindPrefereneceSummaryToValue(sectionId);
        }

        private void bindPrefereneceSummaryToValue(Preference preference) {
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            if(preference instanceof ListPreference) {
                String values = sharedPrefs.getString(preference.getKey(), null);
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

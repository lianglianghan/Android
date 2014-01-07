package com.liangliang.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class MySettingFragment extends PreferenceFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.setting_preference);
	}
}

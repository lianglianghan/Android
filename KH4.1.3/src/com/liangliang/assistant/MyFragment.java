package com.liangliang.assistant;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * ì­Éýfragment
 * 
 * @author Steven
 * 
 */
public class MyFragment extends Fragment {

	private String[] description = {};

	private int[] pictures = {};

	public String[] getDescription() {
		return description;
	}

	public void setDescription(String[] description) {
		this.description = description;
	}

	public int[] getPictures() {
		return pictures;
	}

	public void setPictures(int[] pictures) {
		this.pictures = pictures;
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View view = inflater.inflate(R.layout.myfragment, null);
		ListView mListView = (ListView) view.findViewById(R.id.surge_lv);
		mListView.setAdapter(new MyAdapter(inflater, pictures, description));

		return view;
	}

}

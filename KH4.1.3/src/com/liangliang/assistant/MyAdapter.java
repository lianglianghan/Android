package com.liangliang.assistant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private int[] mPicutes;
	private String[] mDescription;
	
	public MyAdapter(LayoutInflater mInflater,int [] mPictures,String []mDescrition){
		this.mInflater=mInflater;
		this.mPicutes=mPictures;
		this.mDescription=mDescrition;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDescription.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		View view = mInflater.inflate(R.layout.list_item, null);
		TextView tv1=(TextView) view.findViewById(R.id.tv1);
		tv1.setText(position+1+"");
		
		ImageView iv=(ImageView) view.findViewById(R.id.mIv);
		iv.setImageResource(mPicutes[position]);
		
		TextView tv2=(TextView) view.findViewById(R.id.tv2);
		tv2.setText(mDescription[position]);
		
		return view;
	}

}

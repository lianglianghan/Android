package com.liangliang.mytirp;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	// 上下文对象
	private Context mcontext;

	//用来获取宽度
	private WindowManager vm;
	private DisplayMetrics dm;
	
	//宽度和高度
	private int x;
	
	// 图片数组
	private Integer[] imagesId = { 
			R.drawable.icon_home_plane,
			R.drawable.icon_home_train, R.drawable.icon_home_car,
			R.drawable.icon_home_hotel, R.drawable.icon_home_myctrip,
			R.drawable.icon_home_group, R.drawable.icon_home_travel,
			R.drawable.icon_home_tickets, R.drawable.icon_home_destination };

	//颜色数组
	private Integer [] colors={
			R.color.plane,R.color.trainBrg,R.color.carnBrg,
			R.color.hotelBrg,R.color.mycolor,R.color.groupBrg,
			R.color.travelBrg,R.color.ticketsBrg,R.color.destinationBrg
	};
	
	//文字数组
	private Integer [] texts={
		R.string.plane,R.string.train,R.string.car,
		R.string.hotel,R.string.my,R.string.group,
		R.string.travel,R.string.ticket,R.string.destination
	};
	
	@SuppressWarnings("deprecation")
	public MyAdapter(Context c) {
		mcontext = c;
		
		vm=(WindowManager) mcontext.getSystemService(
				Context.WINDOW_SERVICE);
		
		//x=(vm.getDefaultDisplay().getWidth()-30)/3;
		
		dm=new DisplayMetrics();
		vm.getDefaultDisplay().getMetrics(dm);
		x=(dm.widthPixels-30)/3;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imagesId.length;
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
//		ImageView myImageview;
//
//		if (convertView == null) {
//			// 给imageview设置资源
//			myImageview = new ImageView(mcontext);
//			// 设置布局图片显示
//			
//			
//			myImageview.setLayoutParams(new GridView.LayoutParams(x, x));
//		} else {
//			myImageview = (ImageView) convertView;
//		}
//
//		myImageview.setImageResource(imagesId[position]);
////		int color=Resources.getSystem().getColor(R.color.planeBrg);
////		myImageview.setBackgroundColor(color);
//		
//		myImageview.setBackgroundColor(mcontext.getResources().
//				getColor(colors[position]));
//		
//		myImageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
//
//		return myImageview;
		
		LayoutInflater inflater=((Activity)mcontext).getLayoutInflater();
		View myView;
		
		if (convertView == null) {
			// 给imageview设置资源
			myView=inflater.inflate(R.layout.mygridviewitem, null);
			// 设置布局图片显示
			
			myView.setLayoutParams(new GridView.LayoutParams(x, x));
		} else {
			myView = (View) convertView;
		}
		
		ImageView mimage=(ImageView) myView.findViewById(R.id.myimage);
		
		mimage.setImageResource(imagesId[position]);
		
		TextView mtext=(TextView) myView.findViewById(R.id.mytext);
		
		if(position==4){
			mtext=(TextView) myView.findViewById(R.id.belowMtext);
		}
		
		mtext.setText(texts[position]);
		
		if(position==4){
			//mtext.set
			mtext.setTextColor(mcontext.getResources().
					getColor(R.color.mytriptext));
		}
		
		myView.setBackgroundColor(mcontext.getResources().
				getColor(colors[position]));
		
		return myView;
	}

}

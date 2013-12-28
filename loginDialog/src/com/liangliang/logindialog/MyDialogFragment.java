package com.liangliang.logindialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;


public class MyDialogFragment extends DialogFragment{

	public interface myDialogListener{
		public void onPositiveClick(DialogFragment dialog);
		public void onNegativeClick(DialogFragment dialog);
	}
	
	myDialogListener mListener;
	
	private View view;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		mListener=(myDialogListener) activity;
	}
	
	//返回当前dialog中的view
	public View getMyDialogView(){
		return view;
	}
	

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		//要使用getLayoutInflater()前首先要获得Activity,必须要先用
		//getActivity（）获得activity,LayouInflater是用来查找layout中
		//的xml，动态的加载布局后，然后再通过fingViewById来查找各个控件
		AlertDialog.Builder builder=new Builder(getActivity());
		LayoutInflater inflater=getActivity().getLayoutInflater();
		view=inflater.inflate(R.layout.login, null, false);
		
		builder.setView(view);
		builder.setTitle("登陆");
		
		Button myOk=(Button) view.findViewById(R.id.ok);
		Button myCancle=(Button)view.findViewById(R.id.cancel);
		
		myOk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mListener.onPositiveClick(MyDialogFragment.this);
			}
		});
		
		myCancle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mListener.onNegativeClick(MyDialogFragment.this);
			}
		});
		
		return builder.create();
	}


	
}

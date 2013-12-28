package com.liangliang.logindialog;

import com.liangliang.logindialog.MyDialogFragment.myDialogListener;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements myDialogListener {

	private Button myButton;
	private TextView myTextView;

	public void showMyDialog() {
		DialogFragment myDialog = new MyDialogFragment();
		myDialog.show(getSupportFragmentManager(), "MyDialogFragment");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myButton = (Button) findViewById(R.id.btLogin);
		myTextView = (TextView) findViewById(R.id.tvInformation);

		myButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				showMyDialog();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onPositiveClick(DialogFragment dialog) {
		// TODO Auto-generated method stub

		// 这里要获得用户名，现在缺少的是获得到对话框中的xml布局;

		// myTextView.setText("用户名为：123");
		// View mview=dialog.getView();
		// EditText mEditText=(EditText) mview.findViewById(R.id.etUsername);
		//
		// Toast.makeText(this, mEditText.getText(), 0).show();

		// Toast.makeText(this, mview.getId()+"", 0).show();
		// String userName=mview.findViewById(R.id.etUsername).toString();
		// myTextView.setText(userName);

		// String userName=dialog.findViewById(R.id.etUsername).toString();

		// EditText mEditText=(EditText)view.findViewById(R.id.etUsername);
		// myTextView.setText(mEditText.getText());

		View view = ((MyDialogFragment) dialog).getMyDialogView();

		EditText mEditText = (EditText) view.findViewById(R.id.etUsername);

		if (TextUtils.isEmpty(mEditText.getText())) {
			Toast.makeText(this, "用户名不能为空", 0).show();
		} else {
			String info = "您的用户名为: " + mEditText.getText();
			myTextView.setText(info);
			dialog.dismiss();
		}

	}

	@Override
	public void onNegativeClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		dialog.dismiss();
		Toast.makeText(this, "您点击了cancel", 0).show();
		// myTextView.setText("");
	}

}

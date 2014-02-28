package com.liangliang.diyview;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText et_number;

	private MyView myView;

	private ClockView clockView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_number = (EditText) this.findViewById(R.id.et_number);
		myView = (MyView) this.findViewById(R.id.myView1);
		clockView = (ClockView) this.findViewById(R.id.clock_view);

		// 开启线程更新clock的画面
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				while (!Thread.currentThread().isInterrupted()) {
					try {
						// 当前休眠一秒
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					clockView.postInvalidate();
				}

			}
		}).start();

	}

	// 向自定义的控件中输入值
	public void Show(View view) {
		myView.setText(et_number.getText().toString().trim());
		et_number.setText("");
	}
}

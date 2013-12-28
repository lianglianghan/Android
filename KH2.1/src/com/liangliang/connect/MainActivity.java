package com.liangliang.connect;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.LinearGradient;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private ImageButton ibPhoneAdd, ibPhoneDelete, ibMailAdd, ibMailDelete,
			ibAddressAdd, ibAddressDelete,ibSnsAdd,ibSnsDelete;

	// 添加的新的布局方式
	private LinearLayout mlLayout;

	// 四种动态增加的ArrayList,用来动态添加控件
	// 分别用来管理四种控件
	ArrayList<Integer> mArray = new ArrayList<Integer>();
	ArrayList<Integer> mArray1 = new ArrayList<Integer>();
	ArrayList<Integer> mArray2 = new ArrayList<Integer>();
	ArrayList<Integer> mArray3 = new ArrayList<Integer>();
	
	// 从xml中取得各个控件
	private void init() {
		// 电话的增删按钮
		ibPhoneAdd = (ImageButton) findViewById(R.id.ibPhoneAdd);
		ibPhoneDelete = (ImageButton) findViewById(R.id.ibPhoneDelete);

		// 电子邮件
		ibMailAdd = (ImageButton) findViewById(R.id.ibMailAdd);
		ibMailDelete = (ImageButton) findViewById(R.id.ibMailDelete);

		// 地址
		ibAddressAdd = (ImageButton) findViewById(R.id.ibAddressAdd);
		ibAddressDelete = (ImageButton) findViewById(R.id.ibAddressDelete);
		
		//社交工具
		ibSnsAdd=(ImageButton) findViewById(R.id.ibSnsAdd);
		ibSnsDelete=(ImageButton) findViewById(R.id.ibSnsDelete);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();

		//处理各个控件的点击事件
		ibPhoneAdd.setOnClickListener(this);
		ibPhoneDelete.setOnClickListener(this);
		ibMailAdd.setOnClickListener(this);
		ibMailDelete.setOnClickListener(this);
		ibAddressAdd.setOnClickListener(this);
		ibAddressDelete.setOnClickListener(this);
		ibSnsAdd.setOnClickListener(this);
		ibSnsDelete.setOnClickListener(this);

	}

	// 动态创建控件的函数
	public void dyCreate(int id) {
		String tag = "test";
		switch (id) {
		// 增加电话
		case 0:

			// 增加控件
			if (mArray.size() < 9) {
				addPhone();
			}

			break;
		// 删除电话
		case 1:
			deletePhone();

			break;

		// 添加邮件
		case 2:

			if (mArray1.size() < 9) {
				this.addMail();
			}

			break;

		// 删除邮件
		case 3:
			

			deleteMail();

			break;

		// 添加地址
		case 4:
			if (mArray1.size() < 9) {
				this.addAddress();
			}
			
			break;

		// 删除地址
		case 5:

			deleteAddress();
			break;
			
		//添加社交工具
		case 6:
			
			if(mArray3.size()<9){
				this.addSns();
			}
			
			break;
			
		//删除社交工具
		case 7:
			
			deleteSns();
			
			break;
		}

	}

	//删除社交工具
	private void deleteSns() {
		mlLayout = (LinearLayout) findViewById(R.id.layoutSns);

		if (mArray3.size() > 0) {
			//Log.i(tag, mArray3.size() + "");
			RelativeLayout temp = (RelativeLayout) findViewById(mArray3
					.size() + 30);

			// Log.i(tag, mArray2.size() + 10 + "");

			temp.removeAllViews();

			//Log.i(tag, "删除邮件测试");

			mlLayout.removeView(temp);

			mArray3.remove(mArray3.size() - 1);
		}
	}

	//删除地址
	private void deleteAddress() {
		mlLayout = (LinearLayout) findViewById(R.id.layoutAddress);

		if (mArray2.size() > 0) {
			//Log.i(tag, mArray2.size() + "");
			RelativeLayout temp = (RelativeLayout) findViewById(mArray2
					.size() + 20);

			// Log.i(tag, mArray2.size() + 10 + "");

			temp.removeAllViews();

			//Log.i(tag, "删除邮件测试");

			mlLayout.removeView(temp);

			mArray2.remove(mArray2.size() - 1);
		}
	}
	
	//删除邮件地址
	private void deleteMail() {
		mlLayout = (LinearLayout) findViewById(R.id.layoutMail);
		if (mArray1.size() > 0) {
			//Log.i(tag, mArray1.size() + "");
			RelativeLayout temp = (RelativeLayout) findViewById(mArray1
					.size() + 10);

			//Log.i(tag, mArray1.size() + 10 + "");

			temp.removeAllViews();

			//Log.i(tag, "删除邮件测试");

			mlLayout.removeView(temp);

			mArray1.remove(mArray1.size() - 1);
		}
	}

	//删除电话
	private void deletePhone() {
		mlLayout = (LinearLayout) findViewById(R.id.layoutPhone);

		if (mArray.size() > 0) {
			//Log.i(tag, mArray.size() + "");
			RelativeLayout temp = (RelativeLayout) findViewById(mArray
					.size());

			temp.removeAllViews();

			//Log.i(tag, "删除电话测试");

			mlLayout.removeView(temp);

			mArray.remove(mArray.size() - 1);
		}
	}
	
	//添加社交工具
	
	private void addSns(){
		
		mlLayout = (LinearLayout) findViewById(R.id.layoutSns);

		// 建立一个临时布局线性布局
		RelativeLayout tempReLayout = new RelativeLayout(this);
		// 此处应该避免和前面的id相同,应该对mArray1.size（）进行相应的处理
		// 此处id应该是动态的，而且和mArray1.size()有一定的关系
		tempReLayout.setId(mArray3.size() + 1 + 30);
		mArray3.add(mArray3.size());

		RelativeLayout.LayoutParams RLp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		// RLp.setMargins(10, 12, 10, 0);

		Button bt = new Button(this);

		bt.setId(Integer.parseInt(tempReLayout.getId() + "01"));

		//String tag = "测试变量";
		//Log.i(tag, bt.getId() + "");

		RelativeLayout.LayoutParams Rbt = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		Rbt.setMargins(10, 12, 0, 0);

		tempReLayout.addView(bt, Rbt);

		EditText et = new EditText(this);
		// et.setHint("固定电话");
		switch (mArray3.size()) {
		case 1:
			bt.setText("msn");
			et.setHint("msn账号");
			break;
		case 2:
			bt.setText("qq");
			et.setHint("qq账号");
			break;
		default:
			bt.setText("其他");
			et.setHint("sns账号");
			break;
		}
		et.setId(Integer.parseInt(tempReLayout.getId() + "02"));

		RelativeLayout.LayoutParams Ret = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		Ret.addRule(RelativeLayout.RIGHT_OF, bt.getId());
		Ret.setMargins(2, 10, 40, 0);

		tempReLayout.addView(et, Ret);

		// Log.i(tag, "我进来了1");

		mlLayout.addView(tempReLayout, RLp);
	}

	// 添加通信地址
	private void addAddress() {

		mlLayout = (LinearLayout) findViewById(R.id.layoutAddress);

		// 建立一个临时布局线性布局
		RelativeLayout tempReLayout = new RelativeLayout(this);
		tempReLayout.setId(mArray2.size() + 1 + 20);
		mArray2.add(mArray2.size());

		RelativeLayout.LayoutParams RLp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		Button bt = new Button(this);

		bt.setId(Integer.parseInt(tempReLayout.getId() + "01"));

		RelativeLayout.LayoutParams Rbt = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		Rbt.setMargins(10, 60, 6, 0);

		tempReLayout.addView(bt, Rbt);

		switch (mArray2.size()) {
		case 1:
			bt.setText("公司");
			break;

		default:
			bt.setText("其他");
			break;
		}

		EditText et = new EditText(this);
		EditText et1 = new EditText(this);
		EditText et2 = new EditText(this);
		et.setHint("街道");
		et1.setHint("城市");
		et2.setHint("省/直辖市/自治区");

		et.setId(Integer.parseInt(tempReLayout.getId() + "02"));
		et1.setId(Integer.parseInt(tempReLayout.getId() + "03"));
		et2.setId(Integer.parseInt(tempReLayout.getId() + "04"));

		RelativeLayout.LayoutParams Ret = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		Ret.addRule(RelativeLayout.RIGHT_OF, bt.getId());
		Ret.setMargins(0, 18, 40, 0);
		tempReLayout.addView(et, Ret);

		// 城市
		RelativeLayout.LayoutParams Ret1 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		Ret1.addRule(RelativeLayout.RIGHT_OF, bt.getId());
		Ret1.addRule(RelativeLayout.BELOW, et.getId());
		Ret1.setMargins(0, 10, 40, 0);
		tempReLayout.addView(et1, Ret1);

		// 省
		RelativeLayout.LayoutParams Ret2 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		Ret2.addRule(RelativeLayout.RIGHT_OF, bt.getId());
		Ret2.addRule(RelativeLayout.BELOW, et1.getId());
		Ret2.setMargins(0, 10, 40, 0);
		tempReLayout.addView(et2, Ret2);

		// Log.i(tag, "我进来了1");

		mlLayout.addView(tempReLayout, RLp);

	}

	// 增加电子邮件
	private void addMail() {
		mlLayout = (LinearLayout) findViewById(R.id.layoutMail);
		// 建立一个临时布局线性布局
		RelativeLayout tempReLayout = new RelativeLayout(this);
		// 此处应该避免和前面的id相同,应该对mArray1.size（）进行相应的处理
		// 此处id应该是动态的，而且和mArray1.size()有一定的关系
		tempReLayout.setId(mArray1.size() + 1 + 10);
		mArray1.add(mArray1.size());

		RelativeLayout.LayoutParams RLp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		// RLp.setMargins(10, 12, 10, 0);

		Button bt = new Button(this);

		bt.setId(Integer.parseInt(tempReLayout.getId() + "01"));

		RelativeLayout.LayoutParams Rbt = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		Rbt.setMargins(10, 12, 0, 0);

		tempReLayout.addView(bt, Rbt);

		EditText et = new EditText(this);
		// et.setHint("固定电话");
		switch (mArray1.size()) {
		case 1:
			bt.setText("办公");
			et.setHint("电子邮件");
			break;

		default:
			bt.setText("其他");
			et.setHint("电子邮件");
			break;
		}
		et.setId(Integer.parseInt(tempReLayout.getId() + "02"));

		RelativeLayout.LayoutParams Ret = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		Ret.addRule(RelativeLayout.RIGHT_OF, bt.getId());
		Ret.setMargins(2, 10, 40, 0);

		tempReLayout.addView(et, Ret);

		// Log.i(tag, "我进来了1");

		mlLayout.addView(tempReLayout, RLp);
	}

	// 增加电话控件
	private void addPhone() {

		// mArray.add(0);
		// Toast.makeText(this, mArray.size()+"",
		// Toast.LENGTH_SHORT).show();

		// 该段函数需要进行封装，封装成一个函数否则后期会非常复杂
		// 思路：采用ArrayList来实现动态添加
		// id设置方式比如：101 102 103 或者1_1 1_2 1_3

		// 控件的个数受限，最大10个
		// 1 2 3 4 5 6 7 8 9
		// 11 12 21 22 31 32 41 42

		mlLayout = (LinearLayout) findViewById(R.id.layoutPhone);

		// 建立一个临时布局线性布局
		RelativeLayout tempReLayout = new RelativeLayout(this);
		tempReLayout.setId(mArray.size() + 1);
		mArray.add(mArray.size());

		RelativeLayout.LayoutParams RLp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		// RLp.setMargins(10, 12, 10, 0);

		Button bt = new Button(this);

		bt.setId(Integer.parseInt(tempReLayout.getId() + "01"));

		RelativeLayout.LayoutParams Rbt = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		Rbt.setMargins(10, 12, 0, 0);

		tempReLayout.addView(bt, Rbt);

		EditText et = new EditText(this);
		// et.setHint("固定电话");
		switch (mArray.size()) {
		case 1:
			bt.setText("固话");
			et.setHint("固定电话");
			break;
		case 2:
			bt.setText("小灵通");
			et.setHint("小灵通");
			break;
		case 3:
			bt.setText("公司电话");
			et.setHint("公司电话");
			break;

		default:
			bt.setText("其他");
			et.setHint("其他");
			break;
		}
		et.setId(Integer.parseInt(tempReLayout.getId() + "02"));

		RelativeLayout.LayoutParams Ret = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		Ret.addRule(RelativeLayout.RIGHT_OF, bt.getId());
		Ret.setMargins(2, 10, 40, 0);

		tempReLayout.addView(et, Ret);

		// Log.i(tag, "我进来了1");

		mlLayout.addView(tempReLayout, RLp);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		// 电话
		case R.id.ibPhoneAdd:
			//Toast.makeText(this, "增加电话", Toast.LENGTH_SHORT).show();
			dyCreate(0);
			break;
		case R.id.ibPhoneDelete:
			//Toast.makeText(this, "删除电话", Toast.LENGTH_SHORT).show();
			dyCreate(1);
			break;
		// 电子邮件
		case R.id.ibMailAdd:
			//Toast.makeText(this, "增加电子邮件", Toast.LENGTH_SHORT).show();
			dyCreate(2);
			break;

		case R.id.ibMailDelete:
			//Toast.makeText(this, "删除电子邮件", Toast.LENGTH_SHORT).show();
			dyCreate(3);
			break;
		// 地址
		case R.id.ibAddressAdd:
			//Toast.makeText(this, "增加地址", Toast.LENGTH_SHORT).show();
			dyCreate(4);
			break;

		case R.id.ibAddressDelete:

			//Toast.makeText(this, "删除地址", Toast.LENGTH_SHORT).show();

			dyCreate(5);
			break;
			
		case R.id.ibSnsAdd:
			
			dyCreate(6);
			break;
		case R.id.ibSnsDelete:
			dyCreate(7);
			break;
		default:
			break;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

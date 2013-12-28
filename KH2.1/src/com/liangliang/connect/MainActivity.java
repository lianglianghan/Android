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

	// ��ӵ��µĲ��ַ�ʽ
	private LinearLayout mlLayout;

	// ���ֶ�̬���ӵ�ArrayList,������̬��ӿؼ�
	// �ֱ������������ֿؼ�
	ArrayList<Integer> mArray = new ArrayList<Integer>();
	ArrayList<Integer> mArray1 = new ArrayList<Integer>();
	ArrayList<Integer> mArray2 = new ArrayList<Integer>();
	ArrayList<Integer> mArray3 = new ArrayList<Integer>();
	
	// ��xml��ȡ�ø����ؼ�
	private void init() {
		// �绰����ɾ��ť
		ibPhoneAdd = (ImageButton) findViewById(R.id.ibPhoneAdd);
		ibPhoneDelete = (ImageButton) findViewById(R.id.ibPhoneDelete);

		// �����ʼ�
		ibMailAdd = (ImageButton) findViewById(R.id.ibMailAdd);
		ibMailDelete = (ImageButton) findViewById(R.id.ibMailDelete);

		// ��ַ
		ibAddressAdd = (ImageButton) findViewById(R.id.ibAddressAdd);
		ibAddressDelete = (ImageButton) findViewById(R.id.ibAddressDelete);
		
		//�罻����
		ibSnsAdd=(ImageButton) findViewById(R.id.ibSnsAdd);
		ibSnsDelete=(ImageButton) findViewById(R.id.ibSnsDelete);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();

		//��������ؼ��ĵ���¼�
		ibPhoneAdd.setOnClickListener(this);
		ibPhoneDelete.setOnClickListener(this);
		ibMailAdd.setOnClickListener(this);
		ibMailDelete.setOnClickListener(this);
		ibAddressAdd.setOnClickListener(this);
		ibAddressDelete.setOnClickListener(this);
		ibSnsAdd.setOnClickListener(this);
		ibSnsDelete.setOnClickListener(this);

	}

	// ��̬�����ؼ��ĺ���
	public void dyCreate(int id) {
		String tag = "test";
		switch (id) {
		// ���ӵ绰
		case 0:

			// ���ӿؼ�
			if (mArray.size() < 9) {
				addPhone();
			}

			break;
		// ɾ���绰
		case 1:
			deletePhone();

			break;

		// ����ʼ�
		case 2:

			if (mArray1.size() < 9) {
				this.addMail();
			}

			break;

		// ɾ���ʼ�
		case 3:
			

			deleteMail();

			break;

		// ��ӵ�ַ
		case 4:
			if (mArray1.size() < 9) {
				this.addAddress();
			}
			
			break;

		// ɾ����ַ
		case 5:

			deleteAddress();
			break;
			
		//����罻����
		case 6:
			
			if(mArray3.size()<9){
				this.addSns();
			}
			
			break;
			
		//ɾ���罻����
		case 7:
			
			deleteSns();
			
			break;
		}

	}

	//ɾ���罻����
	private void deleteSns() {
		mlLayout = (LinearLayout) findViewById(R.id.layoutSns);

		if (mArray3.size() > 0) {
			//Log.i(tag, mArray3.size() + "");
			RelativeLayout temp = (RelativeLayout) findViewById(mArray3
					.size() + 30);

			// Log.i(tag, mArray2.size() + 10 + "");

			temp.removeAllViews();

			//Log.i(tag, "ɾ���ʼ�����");

			mlLayout.removeView(temp);

			mArray3.remove(mArray3.size() - 1);
		}
	}

	//ɾ����ַ
	private void deleteAddress() {
		mlLayout = (LinearLayout) findViewById(R.id.layoutAddress);

		if (mArray2.size() > 0) {
			//Log.i(tag, mArray2.size() + "");
			RelativeLayout temp = (RelativeLayout) findViewById(mArray2
					.size() + 20);

			// Log.i(tag, mArray2.size() + 10 + "");

			temp.removeAllViews();

			//Log.i(tag, "ɾ���ʼ�����");

			mlLayout.removeView(temp);

			mArray2.remove(mArray2.size() - 1);
		}
	}
	
	//ɾ���ʼ���ַ
	private void deleteMail() {
		mlLayout = (LinearLayout) findViewById(R.id.layoutMail);
		if (mArray1.size() > 0) {
			//Log.i(tag, mArray1.size() + "");
			RelativeLayout temp = (RelativeLayout) findViewById(mArray1
					.size() + 10);

			//Log.i(tag, mArray1.size() + 10 + "");

			temp.removeAllViews();

			//Log.i(tag, "ɾ���ʼ�����");

			mlLayout.removeView(temp);

			mArray1.remove(mArray1.size() - 1);
		}
	}

	//ɾ���绰
	private void deletePhone() {
		mlLayout = (LinearLayout) findViewById(R.id.layoutPhone);

		if (mArray.size() > 0) {
			//Log.i(tag, mArray.size() + "");
			RelativeLayout temp = (RelativeLayout) findViewById(mArray
					.size());

			temp.removeAllViews();

			//Log.i(tag, "ɾ���绰����");

			mlLayout.removeView(temp);

			mArray.remove(mArray.size() - 1);
		}
	}
	
	//����罻����
	
	private void addSns(){
		
		mlLayout = (LinearLayout) findViewById(R.id.layoutSns);

		// ����һ����ʱ�������Բ���
		RelativeLayout tempReLayout = new RelativeLayout(this);
		// �˴�Ӧ�ñ����ǰ���id��ͬ,Ӧ�ö�mArray1.size����������Ӧ�Ĵ���
		// �˴�idӦ���Ƕ�̬�ģ����Һ�mArray1.size()��һ���Ĺ�ϵ
		tempReLayout.setId(mArray3.size() + 1 + 30);
		mArray3.add(mArray3.size());

		RelativeLayout.LayoutParams RLp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		// RLp.setMargins(10, 12, 10, 0);

		Button bt = new Button(this);

		bt.setId(Integer.parseInt(tempReLayout.getId() + "01"));

		//String tag = "���Ա���";
		//Log.i(tag, bt.getId() + "");

		RelativeLayout.LayoutParams Rbt = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		Rbt.setMargins(10, 12, 0, 0);

		tempReLayout.addView(bt, Rbt);

		EditText et = new EditText(this);
		// et.setHint("�̶��绰");
		switch (mArray3.size()) {
		case 1:
			bt.setText("msn");
			et.setHint("msn�˺�");
			break;
		case 2:
			bt.setText("qq");
			et.setHint("qq�˺�");
			break;
		default:
			bt.setText("����");
			et.setHint("sns�˺�");
			break;
		}
		et.setId(Integer.parseInt(tempReLayout.getId() + "02"));

		RelativeLayout.LayoutParams Ret = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		Ret.addRule(RelativeLayout.RIGHT_OF, bt.getId());
		Ret.setMargins(2, 10, 40, 0);

		tempReLayout.addView(et, Ret);

		// Log.i(tag, "�ҽ�����1");

		mlLayout.addView(tempReLayout, RLp);
	}

	// ���ͨ�ŵ�ַ
	private void addAddress() {

		mlLayout = (LinearLayout) findViewById(R.id.layoutAddress);

		// ����һ����ʱ�������Բ���
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
			bt.setText("��˾");
			break;

		default:
			bt.setText("����");
			break;
		}

		EditText et = new EditText(this);
		EditText et1 = new EditText(this);
		EditText et2 = new EditText(this);
		et.setHint("�ֵ�");
		et1.setHint("����");
		et2.setHint("ʡ/ֱϽ��/������");

		et.setId(Integer.parseInt(tempReLayout.getId() + "02"));
		et1.setId(Integer.parseInt(tempReLayout.getId() + "03"));
		et2.setId(Integer.parseInt(tempReLayout.getId() + "04"));

		RelativeLayout.LayoutParams Ret = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		Ret.addRule(RelativeLayout.RIGHT_OF, bt.getId());
		Ret.setMargins(0, 18, 40, 0);
		tempReLayout.addView(et, Ret);

		// ����
		RelativeLayout.LayoutParams Ret1 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		Ret1.addRule(RelativeLayout.RIGHT_OF, bt.getId());
		Ret1.addRule(RelativeLayout.BELOW, et.getId());
		Ret1.setMargins(0, 10, 40, 0);
		tempReLayout.addView(et1, Ret1);

		// ʡ
		RelativeLayout.LayoutParams Ret2 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		Ret2.addRule(RelativeLayout.RIGHT_OF, bt.getId());
		Ret2.addRule(RelativeLayout.BELOW, et1.getId());
		Ret2.setMargins(0, 10, 40, 0);
		tempReLayout.addView(et2, Ret2);

		// Log.i(tag, "�ҽ�����1");

		mlLayout.addView(tempReLayout, RLp);

	}

	// ���ӵ����ʼ�
	private void addMail() {
		mlLayout = (LinearLayout) findViewById(R.id.layoutMail);
		// ����һ����ʱ�������Բ���
		RelativeLayout tempReLayout = new RelativeLayout(this);
		// �˴�Ӧ�ñ����ǰ���id��ͬ,Ӧ�ö�mArray1.size����������Ӧ�Ĵ���
		// �˴�idӦ���Ƕ�̬�ģ����Һ�mArray1.size()��һ���Ĺ�ϵ
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
		// et.setHint("�̶��绰");
		switch (mArray1.size()) {
		case 1:
			bt.setText("�칫");
			et.setHint("�����ʼ�");
			break;

		default:
			bt.setText("����");
			et.setHint("�����ʼ�");
			break;
		}
		et.setId(Integer.parseInt(tempReLayout.getId() + "02"));

		RelativeLayout.LayoutParams Ret = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		Ret.addRule(RelativeLayout.RIGHT_OF, bt.getId());
		Ret.setMargins(2, 10, 40, 0);

		tempReLayout.addView(et, Ret);

		// Log.i(tag, "�ҽ�����1");

		mlLayout.addView(tempReLayout, RLp);
	}

	// ���ӵ绰�ؼ�
	private void addPhone() {

		// mArray.add(0);
		// Toast.makeText(this, mArray.size()+"",
		// Toast.LENGTH_SHORT).show();

		// �öκ�����Ҫ���з�װ����װ��һ������������ڻ�ǳ�����
		// ˼·������ArrayList��ʵ�ֶ�̬���
		// id���÷�ʽ���磺101 102 103 ����1_1 1_2 1_3

		// �ؼ��ĸ������ޣ����10��
		// 1 2 3 4 5 6 7 8 9
		// 11 12 21 22 31 32 41 42

		mlLayout = (LinearLayout) findViewById(R.id.layoutPhone);

		// ����һ����ʱ�������Բ���
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
		// et.setHint("�̶��绰");
		switch (mArray.size()) {
		case 1:
			bt.setText("�̻�");
			et.setHint("�̶��绰");
			break;
		case 2:
			bt.setText("С��ͨ");
			et.setHint("С��ͨ");
			break;
		case 3:
			bt.setText("��˾�绰");
			et.setHint("��˾�绰");
			break;

		default:
			bt.setText("����");
			et.setHint("����");
			break;
		}
		et.setId(Integer.parseInt(tempReLayout.getId() + "02"));

		RelativeLayout.LayoutParams Ret = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		Ret.addRule(RelativeLayout.RIGHT_OF, bt.getId());
		Ret.setMargins(2, 10, 40, 0);

		tempReLayout.addView(et, Ret);

		// Log.i(tag, "�ҽ�����1");

		mlLayout.addView(tempReLayout, RLp);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		// �绰
		case R.id.ibPhoneAdd:
			//Toast.makeText(this, "���ӵ绰", Toast.LENGTH_SHORT).show();
			dyCreate(0);
			break;
		case R.id.ibPhoneDelete:
			//Toast.makeText(this, "ɾ���绰", Toast.LENGTH_SHORT).show();
			dyCreate(1);
			break;
		// �����ʼ�
		case R.id.ibMailAdd:
			//Toast.makeText(this, "���ӵ����ʼ�", Toast.LENGTH_SHORT).show();
			dyCreate(2);
			break;

		case R.id.ibMailDelete:
			//Toast.makeText(this, "ɾ�������ʼ�", Toast.LENGTH_SHORT).show();
			dyCreate(3);
			break;
		// ��ַ
		case R.id.ibAddressAdd:
			//Toast.makeText(this, "���ӵ�ַ", Toast.LENGTH_SHORT).show();
			dyCreate(4);
			break;

		case R.id.ibAddressDelete:

			//Toast.makeText(this, "ɾ����ַ", Toast.LENGTH_SHORT).show();

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

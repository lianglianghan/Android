package com.liangliang.fexplorer;

/**
 * @author 	Steven
 * @name   	mini�ļ�������
 * @version 1.0
 * @description 
 *    1. �г�ָ��·���µ��ļ���Ŀ¼
 *    2. �ܹ���Ŀ¼���ļ�
 *    3. �򿪻���ɾ��ָ�����ļ�
 *    4. ���ظ�Ŀ¼�����ϼ�Ŀ¼
 *    5. �ڵ�ǰĿ¼�´��������ļ�--test.txt(���ݣ�����+��ǰ·����
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.AlteredCharSequence;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView mtv;
	private ListView mListView;
	private TextView list_tv;
	private EditText met;

	// ��ǰ·��
	// private String currentfilePath = "/";

	// �ļ�������
	FileHelper fileHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mListView = (ListView) this.findViewById(R.id.lv);
		met = (EditText) this.findViewById(R.id.et);

		fileHelper = new FileHelper(this, mListView);

		fileHelper.showFileDir();

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				File file = new File(fileHelper.getPaths().get(arg2));
				if (file.canRead()) {
					if (file.isDirectory()) {
						// ��Ŀ¼�Ļ�����ʾĿ¼���ļ�
						fileHelper.setCurrentFilePath(fileHelper.getPaths()
								.get(arg2));
						fileHelper.showFileDir();
					} else {
						// �ļ��Ļ�������ʾdialog��ѡ������Ҫ���еĲ���
						fileHelper.fileHandle(file);
					}
				} else {
					// ����Ȩ�޲���
					Toast.makeText(MainActivity.this, "Ȩ�޲���", 0).show();
				}
			}
		});
	}

	// ���---��Ӧ�ĵ���¼�
	public void explore(View view) {
		String tempfilePath = met.getText().toString();
		if (TextUtils.isEmpty(tempfilePath)) {
			tempfilePath = "/";
		}
		fileHelper.setCurrentFilePath(tempfilePath);
		fileHelper.showFileDir();
	}

	// ����---�µ��ļ�
	public void add(View view) {

		fileHelper.addNewFile();
	}

}

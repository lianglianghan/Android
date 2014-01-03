package com.liangliang.fexplorer;

/**
 * @author 	Steven
 * @name   	mini文件管理器
 * @version 1.0
 * @description 
 *    1. 列出指定路径下的文件和目录
 *    2. 能够打开目录和文件
 *    3. 打开或者删除指定的文件
 *    4. 返回根目录或者上级目录
 *    5. 在当前目录下创建测试文件--test.txt(内容：测试+当前路径）
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

	// 当前路径
	// private String currentfilePath = "/";

	// 文件帮助类
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
						// 是目录的话就显示目录的文件
						fileHelper.setCurrentFilePath(fileHelper.getPaths()
								.get(arg2));
						fileHelper.showFileDir();
					} else {
						// 文件的话，就显示dialog来选择你想要进行的操作
						fileHelper.fileHandle(file);
					}
				} else {
					// 弹出权限不足
					Toast.makeText(MainActivity.this, "权限不足", 0).show();
				}
			}
		});
	}

	// 浏览---对应的点击事件
	public void explore(View view) {
		String tempfilePath = met.getText().toString();
		if (TextUtils.isEmpty(tempfilePath)) {
			tempfilePath = "/";
		}
		fileHelper.setCurrentFilePath(tempfilePath);
		fileHelper.showFileDir();
	}

	// 增加---新的文件
	public void add(View view) {

		fileHelper.addNewFile();
	}

}

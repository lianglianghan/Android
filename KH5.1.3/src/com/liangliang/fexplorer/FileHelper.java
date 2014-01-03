package com.liangliang.fexplorer;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FileHelper {

	//根目录
	final private String rootPath="/";
	
	//存放文件的路径和名称
	private List<String> items = null;
	private List<String> paths = null;
	
	private String currentFilePath;

	private Context context;
	private ListView mListView;

	public FileHelper(Context context, ListView listView) {
		this.context = context;
		this.mListView = listView;
		this.currentFilePath="/";
	}

	public FileHelper() {

	}
	
	//在当前目录下增加新的文件
	public void addNewFile(){
		File file = new File(this.currentFilePath);
		if (!file.canWrite()) {
			Toast.makeText(context, "您无权在此添加文件", 0).show();
		} else {

			file = new File(this.currentFilePath, "test.txt");
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
				fos.write(("测试,当前路径为：" + this.getCurrentFilePath())
						.getBytes());
				Toast.makeText(context, "添加文件成功", 0).show();
				this.showFileDir();
				// flag=true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(context, "添加文件失败", 0).show();
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}
	
	//显示当前目录的信息
	public void showFileDir() {

		items = new ArrayList<String>();
		paths = new ArrayList<String>();

		File f = new File(currentFilePath);
		File[] files = f.listFiles();

		 if (!currentFilePath.equals(rootPath)) {  
             items.add("返回根目录");  
             paths.add(rootPath);  
             items.add("返回上一层目录");  
             paths.add(f.getParent());  
         }  
		
		if (files != null) {
			int count = files.length;
			for (int i = 0; i < count; i++) {
				File file = files[i];
				items.add(file.getName());
				paths.add(file.getPath());
			}
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.context,
				R.layout.list_items, R.id.list_tv, this.items);
		mListView.setAdapter(adapter);
	}

	//显示当前目录的信息
	public void showFileDir(String filePath) {

		items = new ArrayList<String>();
		paths = new ArrayList<String>();

		File f = new File(filePath);
		File[] files = f.listFiles();

		if (files != null) {
			int count = files.length;
			for (int i = 0; i < count; i++) {
				File file = files[i];
				items.add(file.getName());
				paths.add(file.getPath());
			}
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.context,
				R.layout.list_items, R.id.list_tv, this.items);
		mListView.setAdapter(adapter);
	}

	//获取名称
	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}

	//获取路径
	public List<String> getPaths() {
		return paths;
	}

	public void setPaths(List<String> paths) {
		this.paths = paths;
	}

	//对文件进行相应的处理
	public void fileHandle(final File file) {
		AlertDialog.Builder builder = new Builder(this.context);
		builder.setTitle("请选择");
		final String[] items = { "查看该文件", "删除该文件" };
		builder.setItems(items, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

				switch (which) {
				case 0:
					//打开文件
					openFile(file);
					//Toast.makeText(context, "查看该文件", 0).show();
					break;
				case 1:
					//删除文件
					AlertDialog.Builder builer1 = new Builder(context);
					builer1.setTitle("注意");
					builer1.setMessage("确定删除文件吗");
					builer1.setPositiveButton("确定", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							file.delete();
							showFileDir();
							Toast.makeText(context, "恭喜你，文件已删除", 0).show();
						}
					});
					builer1.setNegativeButton("取消", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});

					builer1.show();
					break;

				default:
					break;
				}
			}
		});

		builder.show();
	}

	//打开文件
	public void openFile(File f) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);

		/* 调用getMIMEType()来取得MimeType */
		String type = getMIMEType(f);
		/* 设置intent的file与MimeType */
		intent.setDataAndType(Uri.fromFile(f), type);
		this.context.startActivity(intent);
	}

	/* 判断文件MimeType的method */
	//此方法直接调用网上的代码
	private String getMIMEType(File f) {
		String type = "";
		String fName = f.getName();
		/* 取得扩展名 */
		String end = fName
				.substring(fName.lastIndexOf(".") + 1, fName.length())
				.toLowerCase();

		/* 依扩展名的类型决定MimeType */
		if (end.equals("m4a") || end.equals("mp3") || end.equals("mid")
				|| end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
			type = "audio";
		} else if (end.equals("3gp") || end.equals("mp4")) {
			type = "video";
		} else if (end.equals("jpg") || end.equals("gif") || end.equals("png")
				|| end.equals("jpeg") || end.equals("bmp")) {
			type = "image";
		} else if (end.equals("apk")) {
			/* android.permission.INSTALL_PACKAGES */
			type = "application/vnd.android.package-archive";
		} else {
			type = "*";
		}
		/* 如果无法直接打开，就跳出软件列表给用户选择 */
		if (end.equals("apk")) {
		} else {
			type += "/*";
		}
		return type;
	}

	public String getCurrentFilePath() {
		return currentFilePath;
	}

	public void setCurrentFilePath(String currentFilePath) {
		this.currentFilePath = currentFilePath;
	}

}

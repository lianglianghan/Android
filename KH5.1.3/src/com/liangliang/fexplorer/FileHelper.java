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

	//��Ŀ¼
	final private String rootPath="/";
	
	//����ļ���·��������
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
	
	//�ڵ�ǰĿ¼�������µ��ļ�
	public void addNewFile(){
		File file = new File(this.currentFilePath);
		if (!file.canWrite()) {
			Toast.makeText(context, "����Ȩ�ڴ�����ļ�", 0).show();
		} else {

			file = new File(this.currentFilePath, "test.txt");
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
				fos.write(("����,��ǰ·��Ϊ��" + this.getCurrentFilePath())
						.getBytes());
				Toast.makeText(context, "����ļ��ɹ�", 0).show();
				this.showFileDir();
				// flag=true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(context, "����ļ�ʧ��", 0).show();
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
	
	//��ʾ��ǰĿ¼����Ϣ
	public void showFileDir() {

		items = new ArrayList<String>();
		paths = new ArrayList<String>();

		File f = new File(currentFilePath);
		File[] files = f.listFiles();

		 if (!currentFilePath.equals(rootPath)) {  
             items.add("���ظ�Ŀ¼");  
             paths.add(rootPath);  
             items.add("������һ��Ŀ¼");  
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

	//��ʾ��ǰĿ¼����Ϣ
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

	//��ȡ����
	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}

	//��ȡ·��
	public List<String> getPaths() {
		return paths;
	}

	public void setPaths(List<String> paths) {
		this.paths = paths;
	}

	//���ļ�������Ӧ�Ĵ���
	public void fileHandle(final File file) {
		AlertDialog.Builder builder = new Builder(this.context);
		builder.setTitle("��ѡ��");
		final String[] items = { "�鿴���ļ�", "ɾ�����ļ�" };
		builder.setItems(items, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

				switch (which) {
				case 0:
					//���ļ�
					openFile(file);
					//Toast.makeText(context, "�鿴���ļ�", 0).show();
					break;
				case 1:
					//ɾ���ļ�
					AlertDialog.Builder builer1 = new Builder(context);
					builer1.setTitle("ע��");
					builer1.setMessage("ȷ��ɾ���ļ���");
					builer1.setPositiveButton("ȷ��", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							file.delete();
							showFileDir();
							Toast.makeText(context, "��ϲ�㣬�ļ���ɾ��", 0).show();
						}
					});
					builer1.setNegativeButton("ȡ��", new OnClickListener() {

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

	//���ļ�
	public void openFile(File f) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);

		/* ����getMIMEType()��ȡ��MimeType */
		String type = getMIMEType(f);
		/* ����intent��file��MimeType */
		intent.setDataAndType(Uri.fromFile(f), type);
		this.context.startActivity(intent);
	}

	/* �ж��ļ�MimeType��method */
	//�˷���ֱ�ӵ������ϵĴ���
	private String getMIMEType(File f) {
		String type = "";
		String fName = f.getName();
		/* ȡ����չ�� */
		String end = fName
				.substring(fName.lastIndexOf(".") + 1, fName.length())
				.toLowerCase();

		/* ����չ�������;���MimeType */
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
		/* ����޷�ֱ�Ӵ򿪣�����������б���û�ѡ�� */
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

package com.liangliang.settings;

/**
 * @author	Steven
 * @name	Imitate Settings 
 * @version	1.0beta
 * @description
 * 	   1. ���ģ�ͣ�Preference���Լ�PreferenceFragment
 * 	   2. ��Ҫ���ܣ�ģ���ֻ������ý����Լ�����Ӧ��ѡ����б���
 *     3. �������û��ķ�ʽ��ͨ��sharedPreference�ļ�ֵ�Է�ʽ�������
 *     4. xml���õ���preference��PreferenceCategory��PreferenceScreen��
 *     						  CheckboxPreference��RingtonePreference��
 *     						  Preference��SwitchPreference��
 *     5. ��style.xml���Զ�����theme���ı����ı�����ɫ��������ɫ���������嵥�ļ��н���������
 *     6. ���ڵ����⣺�����Զ���������������ϵ��ѡ�����ɫ�������Ÿı�
 *     7. �������������
 */

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		getFragmentManager().beginTransaction().replace(android.R.id.content,
				new MySettingFragment()).commit();

	}

	
}

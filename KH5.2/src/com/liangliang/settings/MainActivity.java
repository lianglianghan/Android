package com.liangliang.settings;

/**
 * @author	Steven
 * @name	Imitate Settings 
 * @version	1.0beta
 * @description
 * 	   1. 类和模型：Preference、以及PreferenceFragment
 * 	   2. 主要功能：模仿手机的设置界面以及对相应的选项进行保存
 *     3. 数据永久化的方式：通过sharedPreference的键值对方式来保存的
 *     4. xml中用到的preference：PreferenceCategory、PreferenceScreen、
 *     						  CheckboxPreference、RingtonePreference、
 *     						  Preference、SwitchPreference等
 *     5. 在style.xml中自定义了theme（改变界面的背景颜色和字体颜色），并在清单文件中进行了声明
 *     6. 存在的问题：采用自定义的主题后，依赖关系的选项的颜色不会随着改变
 *     7. 解决方案：尚无
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

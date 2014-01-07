@author   steven
@appName  settings
@version  1.0beta

功能清单：

1.模仿系统的设置界面，并提供相应的设置选项

2.能够记录用户的选择，程序再次打开后能够自动加载用户的选择

存在的问题：

1.使用自定义的主题后，那些不能够选择的选项的颜色仍然为白色并没有变暗

  解决方案：1.不使用自定义的theme，删除清单文件中的以下语句：
  
             android:theme="@style/perference_set_activity"
          
            2.修改系统中关于preference的相关描述文件，具体做法请自行Google或Baidu

	    3.自定义layout并在preference中加载

项目使用说明：

1.用Eclipse导入工程后，请在libs文件夹下导入支持库（android-support-v4.jar包）

2.请手动删除工程下的readme.txt文件
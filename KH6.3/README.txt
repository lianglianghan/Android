@author   Steven Han
@appName  Data Emulator
@version  1.0
@build	  2014-1-26 17:13:02

app简介：

模拟传感器的数据，并显示在activity中

功能清单：

1.Service 模拟sensor定期(5000ms)产生一定的数据

2.Activity 绑定到Service上，读取Sensor产生的数据，并刷新显示主界面


项目使用说明：

1.用Eclipse导入工程后，请在libs文件夹下导入支持库（android-support-v4.jar包）

2.请手动删除工程下的readme.txt文件
@author   Steven Han
@appName  Simple Launcher
@version  1.0
@build	  2014-1-23 20:46:49

app简介：

app之GridView

功能清单：

1.将系统中安装的application以GridView的方式显示出来

2.为GridView绑定了ContextMenu（上下文菜单，长按每一个item弹出）

3.ContextMenu中包含三个选项：打开应用、删除应用、显示应用详细信息

4.点击打开应用和显示应用详细信息时，会弹出Toast通知

5.点击删除应用时，会在状态栏显示自定义的notification通知


项目使用说明：

1.用Eclipse导入工程后，请在libs文件夹下导入支持库（android-support-v4.jar包）

2.请手动删除工程下的readme.txt文件
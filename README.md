# Reinforce

#### 介绍
加固插件

#### 软件架构
软件架构说明


#### 安装教程

1.  添加插件仓库地址 maven {
    url "http://140.143.153.108:8081/nexus/content/repositories/thirdparty/"
  }

2.  添加插件 classpath 'com.xl:jiagu:1.5'
3.  在app 的gradle 里面应用插件 
    apply plugin:'com.xl.jiagu'
#### 使用说明

1.  在使用过程中，需要进行简单配置

jiagu{
    userName 'XXX'
    password 'XXX'
    jiaguTools '/Users/XXX/Downloads/360jiagubao_mac/jiagu/jiagu.jar'
}
需要配置一下 360加固的 账号，密码 还有360加固包的文件地址

![输入图片说明](https://images.gitee.com/uploads/images/2021/0606/221409_07f02da8_2009037.png "屏幕截图.png")

在插件任务中就可以选择需要加固的版本

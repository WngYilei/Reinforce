# Reinforce

#### 介绍

我是想着整一下Jenkins的部署功能，但是在自己使用过程中由于服务器的运存比较小gradle进程总是被杀死而编译失败，所以就想尝试看看gradle是否可以实现类似于Jenkins的功能，终于在我的不懈努力下 整出来了这款插件。这是一款用于apk 加固，上传蒲公英和钉钉提醒 集一身的插件，在build 完成之后或者执行完assembleRelease任务之后，就会在build中生成apk，然后就可以使用jiagu插件对apk 进行加固操作，加固完成之后会自动上传到蒲公英上，并且通过钉钉机器人 提醒发送下载地址

#### 安装教程

1.  添加插件仓库地址 
    
```
   maven {
      url "http://140.143.153.108:8081/nexus/content/repositories/thirdparty/"
     }
```


2.  添加插件 
    

```
classpath 'com.xl:jiagu:1.7'
```



3.  在app 的gradle 里面应用插件 



```
apply plugin:'com.xl.jiagu'
```


#### 使用说明

1.  在使用过程中，需要进行简单配置


![输入图片说明](https://images.gitee.com/uploads/images/2021/0611/000635_f25b4dd9_2009037.png "屏幕截图.png")



```
jiagu {
    userName '18220810'
    password 'xiaol21'
    jiaguTools '/Users/wyl/Downloads/360jiagubao_mac/jiagu/jiagu.jar'
    uKey 'f2df642bcfc4f53db9beb39'
    apiKey 'eb6b7ec8d3a41c63eb17b7942c'
    vername rootProject.ext.versionName
    appname '加固插件'
    isAtAll true
    access_token '4a38e3af68d721dbb3831b6650279b9a7d97f5e8b25a5e7c4165'
    upload 'https://www.pgyer.com/UJ'
}

```
userName 是360加固所需要的账号
password 是360加固所需要的密码
jiaguTools 是加固.jar 所在的路径
uKey 和apiKey 是上传蒲公英的必须信息，具体可以从蒲公英我的-api信息 模块里进行查看
（蒲公英地址 [https://www.pgyer.com](http://)）
vername  是应用的版本
appname  是应用名称
isAtAll  是配置是否需要在钉钉群中 艾特全体成员
access_token 是钉钉机器人的token，在新建钉钉机器人的时候会提供
（钉钉文档地址 [https://developers.dingtalk.com/document/app/custom-robot-access](http://)）
upload  是应用的下载地址，在蒲公英上传应用后，每个应用都会对应一个下载地址

需要配置一下签名信息
```
  signingConfigs {
        release {
            storeFile file('enjoy.keystore')
            storePassword '123456'
            keyAlias 'enjoy'
            keyPassword '123456'
        }
    }
```

![输入图片说明](https://images.gitee.com/uploads/images/2021/0606/221409_07f02da8_2009037.png "屏幕截图.png")

在插件任务中就可以选择需要加固的版本
然后就可以从控制台看到加固和上传的信息，这样就可以加固，上传，提醒 一条龙服务了
这款插件现在持续优化过程中，希望大家都提一下宝贵意见~


# Reinforce

#### 介绍

这是一款用于apk 加固和上传蒲公英集一身的插件，在build 完成之后或者执行完assembleRelease任务之后，就会在build中生成apk，然后就可以使用jiagu插件
对所需要的apk进行操作

#### 安装教程

1. 添加插件仓库地址

```groovy
 maven {
    url "http://140.143.153.108:8081/nexus/content/repositories/thirdparty/"
}
classpath 'com.xl:reinforce:1.0.16@jar'
```

2. 添加插件

```groovy
classpath 'com.xl:reinforce:1.0.16@jar'
```

3. 在app 的gradle 里面应用插件 apply plugin:'com.xl.jiagu'

#### 使用说明

1. 在使用过程中，需要进行简单配置
   ``

```groovy
apply plugin: 'com.xl.jiagu'
jiagu {
//  加固相关 360加固
    userName '180810'
    password 'xi1'
    jiaguTools '/Users/wyl/Doc/jiagu/jiagu.jar'
//  蒲公英相关
    uKey 'f2df642bcf4f53db9beb39'
    apiKey 'eb6b721fb2aec8d7b7942c'

//  钉钉机器人相关
    access_token '4a3bb8919577eea8e3a9b9a7d97f5e8b25a5e7c4165'

//  发送消息
    upload 'https://www.pgyer.com/UGwJ'
    vername rootProject.ext.versionName
    appname '加固插件'
    isAtAll true
//  钉钉机器人支持自定义消息，如果不配置 msg 将自动读取 其他配置信息，发送默认消息模板  
    msg ''
}
```

需要配置一下 360加固的 账号，密码 还有360加固包的文件地址,uKey 和apiKey 是上传蒲公英的必须信息，具体可以从蒲公英我的-api信息 模块里进行查看

需要配置一下签名信息

```groovy
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

然后就可以从控制台看到加固和上传的信息，这样就解放了双手~

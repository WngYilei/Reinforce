# Reinforce

#### 介绍

这是一款用于apk 加固和上传蒲公英集一身的插件，在build 完成之后或者执行完assembleRelease任务之后，就会在build中生成apk，然后就可以使用jiagu插件 对所需要的apk进行操作

#### 安装教程

1.  添加插件仓库地址 maven {
    url "http://140.143.153.108:8081/nexus/content/repositories/thirdparty/"
  }

2.  添加插件 classpath 'com.xl:jiagu:1.5'
3.  在app 的gradle 里面应用插件 
    apply plugin:'com.xl.jiagu'
#### 使用说明

1.  在使用过程中，需要进行简单配置


![输入图片说明](https://images.gitee.com/uploads/images/2021/0607/085017_23b63268_2009037.png "屏幕截图.png")

jiagu{
    userName 'XXX'



    password 'XXX'


    jiaguTools '/Users/XXX/Downloads/360jiagubao_mac/jiagu/jiagu.jar'


    uKey 'xxx'


    apiKey 'xxx'
}
需要配置一下 360加固的 账号，密码 还有360加固包的文件地址,uKey 和apiKey 是上传蒲公英的必须信息，具体可以从蒲公英我的-api信息 模块里进行查看



需要配置一下签名信息


  signingConfigs {
        release {
            storeFile file('enjoy.keystore')
            storePassword '123456'
            keyAlias 'enjoy'
            keyPassword '123456'
        }
    }


![输入图片说明](https://images.gitee.com/uploads/images/2021/0606/221409_07f02da8_2009037.png "屏幕截图.png")

在插件任务中就可以选择需要加固的版本

然后就可以从控制台看到加固和上传的信息，这样就解放了双手~

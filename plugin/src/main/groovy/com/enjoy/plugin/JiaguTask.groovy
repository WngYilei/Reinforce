package com.enjoy.plugin

import com.android.builder.model.SigningConfig
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class JiaguTask extends DefaultTask {

    Jiagu jiagu
    SigningConfig signingConfig
    File apk

    JiaguTask() {
        group = "jiagu"
    }

    @TaskAction
    def run() {

        if (jiagu.jiaguTools == null || jiagu.userName == null || jiagu.password == null) {
            logger.error("\n>>>>>>请检查加固工具配置")
            return
        }
        project.exec {
            it.commandLine("java", "-jar", jiagu.jiaguTools, "-login", jiagu.userName, jiagu.password)
        }

        if (signingConfig) {
            project.exec {
                it.commandLine("java", "-jar", jiagu.jiaguTools, "-importsign", signingConfig.storeFile.absolutePath,
                        signingConfig.storePassword, signingConfig.keyAlias, signingConfig.keyPassword)
            }
        } else {
            logger.error("\n>>>>>>请检查签名配置")
            return
        }
        project.exec {
            it.commandLine("java", "-jar", jiagu.jiaguTools, "-jiagu", apk.absolutePath, apk.parent, "-autosign")
        }

        if (jiagu.uKey != null && jiagu.apiKey != null) {
            def dir = new File(apk.parent)
            dir.eachFile { file ->
                if (file.name.endsWith(".apk") && file.name.contains("sign")) {
                    project.exec {
                        it.commandLine("curl", "-F", "file=@${file.absolutePath}", "-F", "uKey=${jiagu.uKey}", "-F", "_api_key=${jiagu.apiKey}", "https://www.pgyer.com/apiv1/app/upload")
                    }
                }
            }
        } else {
            logger.warn("\n>>>>>>请检查蒲公英信息配置")
        }

        if (jiagu.isAtAll == null) {
            jiagu.isAtAll = false
        }

        String msg = ""
        if (jiagu.msg == null) {
            msg = "${jiagu.appname}-version:${jiagu.vername} 版本已经发布,发布地址${jiagu.upload}"
        } else {
            msg = jiagu.msg
        }

        if (jiagu.access_token != null) {
            project.exec {
                it.commandLine("curl", "https://oapi.dingtalk.com/robot/send?access_token=${jiagu.access_token}", "-H", "Content-Type: application/json", "-d", "{\n" +
                        "    \"at\": {\n" +
                        "       \"isAtAll\": ${jiagu.isAtAll}\n" +
                        "    },\n" +
                        "    \"text\": {\n" +
                        "        \"content\":\"${msg}\"\n" +
                        "    },\n" +
                        "    \"msgtype\":\"text\"\n" +
                        "}")
            }
        } else {
            logger.warn("\n>>>>>>请检查机器人配置")
        }

    }
}

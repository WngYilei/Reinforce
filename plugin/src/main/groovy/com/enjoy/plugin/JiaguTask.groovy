package com.enjoy.plugin

import com.android.builder.model.SigningConfig
import org.gradle.api.DefaultTask
import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecResult;

class JiaguTask extends DefaultTask {

    Jiagu jiagu
    SigningConfig signingConfig
    File apk

    JiaguTask() {
        group = "jiagu"
    }

    @TaskAction
    def run() {
        project.exec {
            it.commandLine("java", "-jar", jiagu.jiaguTools, "-login", jiagu.userName, jiagu.password)
        }

        if (signingConfig) {
            project.exec {
                it.commandLine("java", "-jar", jiagu.jiaguTools, "-importsign", signingConfig.storeFile.absolutePath,
                        signingConfig.storePassword, signingConfig.keyAlias, signingConfig.keyPassword)
            }
        }
        project.exec {
            it.commandLine("java", "-jar", jiagu.jiaguTools, "-jiagu", apk.absolutePath,
                    apk.parent, "-autosign")
        }

        def dir = new File(apk.parent)
        dir.eachFile { file ->
            if (file.name.endsWith(".apk") && file.name.contains("sign")) {
                project.exec {
                    it.commandLine("curl", "-F", "file=@${file.absolutePath}", "-F", "uKey=${jiagu.uKey}", "-F", "_api_key=${jiagu.apiKey}", "https://www.pgyer.com/apiv1/app/upload")
                }
            }
        }

        project.exec {
            it.commandLine("curl", "https://oapi.dingtalk.com/robot/send?access_token=${jiagu.access_token}", "-H", "Content-Type: application/json", "-d", "{\n" +
                    "    \"at\": {\n" +
                    "       \"isAtAll\": ${jiagu.isAtAll}\n" +
                    "    },\n" +
                    "    \"text\": {\n" +
                    "        \"content\":\"${jiagu.appname}-version:${jiagu.vername} 版本已经发布,发布地址${jiagu.upload}\"\n" +
                    "    },\n" +
                    "    \"msgtype\":\"text\"\n" +
                    "}")
        }


    }
}

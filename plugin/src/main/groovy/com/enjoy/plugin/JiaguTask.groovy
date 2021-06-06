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
                    it.commandLine("curl", "-F", "file=@${file.absolutePath}", "-F", "uKey=f2df642bcfc90c79e4464f53db9beb39", "-F", "_api_key=eb6b721fb2aec8d3a41c63eb17b7942c", "https://www.pgyer.com/apiv1/app/upload")
                }
            }

        }


    }
}

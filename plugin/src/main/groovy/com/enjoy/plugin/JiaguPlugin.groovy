package com.enjoy.plugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.BaseVariantOutput
import com.android.builder.model.SigningConfig
import org.gradle.api.Plugin
import org.gradle.api.Project

class JiaguPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        Jiagu jiagu = project.extensions.create("jiagu", Jiagu)
        project.afterEvaluate {
            AppExtension android = project.extensions.android
            android.applicationVariants.all {
                ApplicationVariant variant ->
                    SigningConfig signingConfig = variant.signingConfig
                    variant.outputs.all {
                        BaseVariantOutput output->
                            File apk = output.outputFile
                            JiaguTask jiaguTask = project.tasks.create("jiagu${variant.baseName.capitalize()}", JiaguTask)
                            jiaguTask.jiagu = jiagu
                            jiaguTask.signingConfig = signingConfig
                            jiaguTask.apk = apk
                    }
            }
        }
    }
}

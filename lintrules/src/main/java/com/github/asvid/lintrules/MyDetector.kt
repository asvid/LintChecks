package com.github.asvid.lintrules

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.JavaContext
import org.jetbrains.uast.UFile


class MyDetector : Detector(), Detector.UastScanner, Detector.ClassScanner {

    override fun getApplicableUastTypes() = listOf(UFile::class.java)

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return object : UElementHandler() {
            override fun visitFile(node: UFile) {
                val imports = node.imports
                val packageName = node.packageName


                val containsInternal = imports.any {
                    it.importReference?.asRenderString()?.contains("internal") ?: false
                }

                if (packageName.contains("external") && containsInternal) {
                    println("LINT: $packageName")

                    imports.forEach {
                        println("import: ${it.importReference?.asRenderString()}")
                    }

                    val fix = fix()
                        .replace()
                        .build()

                    context.report(
                        MyIssue.getIssue(),
                        node,
                        context.getLocation(node),
                        "Contains internal import.",
                        fix
                    )
                }
            }
        }
    }
}
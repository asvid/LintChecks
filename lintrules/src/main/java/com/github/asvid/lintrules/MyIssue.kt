package com.github.asvid.lintrules

import com.android.tools.lint.detector.api.*

object MyIssue {

    private const val ID = "ExposingInternalObjects"
    private const val DESCRIPTION = "internal objects shouldn't be used outside their package"
    private const val EXPLANATION =
        "because."
    private val CATEGORY = Category.COMPLIANCE
    private const val PRIORITY = 4
    private val SEVERITY = Severity.ERROR

    fun getIssue() = Issue.create(
        ID,
        DESCRIPTION,
        EXPLANATION,
        CATEGORY,
        PRIORITY,
        SEVERITY,
        Implementation(
            MyDetector::class.java,
            Scope.JAVA_FILE_SCOPE
        )
    )
}
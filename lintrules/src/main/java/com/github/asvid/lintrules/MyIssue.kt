package com.github.asvid.lintrules

import com.android.tools.lint.detector.api.*
import java.util.*

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
            EnumSet.of(Scope.JAVA_FILE, Scope.TEST_SOURCES)
        )
    )

}
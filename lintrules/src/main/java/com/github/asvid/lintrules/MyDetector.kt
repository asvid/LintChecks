package com.github.asvid.lintrules

import com.android.tools.lint.detector.api.Context
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Location
import com.android.tools.lint.detector.api.XmlContext
import org.jetbrains.uast.UClass
import org.w3c.dom.Document
import java.util.regex.Pattern

private val importPattern = Pattern.compile("(import).*(\\.internal\\.).*")
private val packagePattern = Pattern.compile("(package).*(\\.internal\\.).*")

class MyDetector : Detector(), Detector.UastScanner {

    override fun getApplicableUastTypes() = listOf(UClass::class.java)

    override fun visitDocument(context: XmlContext, document: Document) {
        // Needs to be overridden but we we'll do the work in afterCheckFile.
    }

    override fun afterCheckFile(context: Context) {
        val source = context.getContents().toString()
        val matcher = importPattern.matcher(source)
        val packagePattern = packagePattern.matcher(source)

        while (matcher.find() && !packagePattern.find()) {
            val start = matcher.start()
            val end = matcher.end()

            val location = Location.create(context.file, source, start, end)
            context.report(MyIssue.getIssue(), location, "Contains internal import.")
        }
    }
}
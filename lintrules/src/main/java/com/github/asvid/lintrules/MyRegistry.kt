package com.github.asvid.lintrules

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Issue
import java.util.*

class MyRegistry : IssueRegistry() {

    override val api: Int = com.android.tools.lint.detector.api.CURRENT_API

    override val issues: List<Issue> = Collections.singletonList(MyIssue.getIssue())

}
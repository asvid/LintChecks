package com.github.asvid.lintchecks.external

import com.github.asvid.lintchecks.internal.HiddenStuff

class ExposedApi {

    fun exposedMethod() = "this method is exposed"

    fun esposingInternal() {
        HiddenStuff.getText()
    }

}
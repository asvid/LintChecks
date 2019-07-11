package com.github.asvid.lintchecks.external

import android.util.Log
import com.github.asvid.lintchecks.internal.HiddenStuff

class ExposedApi {

    fun exposedMethod() = "this method is exposed"

    fun esposingInternal() {
        HiddenStuff.getText()
        Log.d("some tag", "some data")
    }

}
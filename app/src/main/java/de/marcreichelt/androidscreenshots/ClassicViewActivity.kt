package de.marcreichelt.androidscreenshots

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class ClassicViewActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this)
        textView.text = "Hello Classic Android Views"
        setContentView(textView)
    }
}

package de.marcreichelt.androidscreenshots

import android.app.Activity
import android.graphics.Bitmap
import android.widget.TextView
import androidx.core.view.drawToBitmap
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.FileOutputStream

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [33])
class ViewTest {

    private lateinit var view: TextView

    @Before
    fun setUp() {
        val activityController = Robolectric.buildActivity(Activity::class.java)
        val activity = activityController.create().get()

        view = TextView(activity)

        activity.setContentView(view)
        activityController.start().resume().visible()
    }

    @Test
    fun `draw a view`() {
        view.text = "Rhein-Main Rocks!"
        view.drawToBitmap().writePng("view.png")

        // tip: use qualifiers = "xxxhdpi"
        // see https://robolectric.org/device-configuration/
    }

}

internal fun Bitmap.writePng(fileName: String) {
    compress(Bitmap.CompressFormat.PNG, 100, FileOutputStream(fileName))
}

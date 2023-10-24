package de.marcreichelt.androidscreenshots

import android.graphics.Bitmap
import android.graphics.Bitmap.Config.ARGB_8888
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.FileOutputStream

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [33])
class CanvasTest {

    @Test
    fun `draw a circle`() {
        val bitmap = Bitmap.createBitmap(200, 200, ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint()

        // white background
        canvas.drawColor(Color.WHITE)

        // red circle
        paint.color = Color.RED
        canvas.drawCircle(100f, 100f, 75f, paint)

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, FileOutputStream("circle.png"))
    }

}
package de.marcreichelt.androidscreenshots

import android.app.Activity
import androidx.core.view.drawToBitmap
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieTask
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.util.concurrent.Executor

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [33], qualifiers = RobolectricDeviceQualifiers.Pixel6)
class LottieTest {

    private lateinit var lottieView: LottieAnimationView

    @Before
    fun setUp() {
        val activityController = Robolectric.buildActivity(Activity::class.java)
        val activity = activityController.create().get()

        LottieTask.EXECUTOR = Executor { it.run() }
        lottieView = LottieAnimationView(activity)

        activity.setContentView(lottieView)
        activityController.start().resume().visible()
    }

    @Test
    fun `having fun with a lottie animation`() {
        lottieView.setAnimation(R.raw.lottielogo)

        val images = 3
        for (progress in 0..<images) {
            lottieView.progress = (progress / images.toFloat())
            lottieView.drawToBitmap().writePng("lottie_$progress.png")
        }
    }

}

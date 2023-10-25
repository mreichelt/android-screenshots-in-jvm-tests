package de.marcreichelt.androidscreenshots

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [33], qualifiers = RobolectricDeviceQualifiers.Pixel6)
class ComposeActivityTest {

    @get:Rule
    val activityComposeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun `snapshot a compose activity`() {
        activityComposeRule.onRoot().captureRoboImage("compose_activity.png")
    }

}

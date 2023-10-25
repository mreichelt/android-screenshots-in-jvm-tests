package de.marcreichelt.androidscreenshots

import androidx.compose.material3.Text
import androidx.compose.ui.test.junit4.createComposeRule
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
class ComposeTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `snapshot a composable`() {
        composeRule.setContent {
            Text("I'm a Composable 😃")
        }
        composeRule.onRoot().captureRoboImage("composable.png")
    }

    @Test
    fun `snapshot the Greeting composable`() {
        composeRule.setContent {
            Greeting("Frankfurt")
        }
        composeRule.onRoot().captureRoboImage("composable_greeting.png")
    }

    @Test
    fun `snapshot with roborazzi-compose`() {
        captureRoboImage("composable_roborazzi-compose.png") {
            Text("roborazzi-compose")
        }
    }

}

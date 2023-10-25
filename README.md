# Talk: Android Screenshots in JVM tests

How to take screenshots in Android JVM (unit) tests using Robolectric and Roborazzi.

This is a talk for the [Android Testing Awesomeness](https://gdg.community.dev/events/details/google-gdg-rhein-main-presents-gdg-meetup-android-testing-awesomeness/)
event from GDG Rhein-Main. Please reach out to me on [Mastodon](https://mastodon.social/@mreichelt)
or [Twitter](https://twitter.com/mreichelt) if you have questions or feedback!

To follow along:

1. Open this project in Android Studio
2. See tests in [the unit test directory](app/src/test/java/de/marcreichelt/androidscreenshots)
3. Run the tests individually (follow guide below)

## CanvasTest

Renders a `Bitmap` by drawing on a `Canvas` and creates a snapshot as a PNG file.

- This is a regular JVM unit test - no Android devices/emulators required!
- This uses Robolectric native mode (introduced in Robolectric 4.10) - this uses a native Skia
  rendering engine from Android, which is fast and more close to what Android actually renders!
- Note the annotations: `@RunWith(RobolectricTestRunner::class)` is needed to run this via Robolectric,
  `@GraphicsMode(GraphicsMode.Mode.NATIVE)` is needed to enable the new native rendering, and
  `@Config(sdk = [33])` is needed because Robolectric 4.10.3 does not support 34 yet.
- Bonus: set a breakpoint to get access to the `bitmap` variable, start test in debugger, and click on 'View Bitmap'!

## ViewTest

Renders a simple `TextView` in an empty `Activity`.

- The `setUp` method shows how to set up any activity class and control the lifecycle manually. The other tools will do this for you!
- The image has a low resolution because the Robolectric default is `mdpi`. You can add a `qualifiers = "xxxhdpi"`
  parameter to the `@Config` to improve it.

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

## ActivityTest

Renders the `ClassicViewActivity` (with a good old Android TextView) using [Roborazzi](https://github.com/takahirom/roborazzi).

- It doesn't do anything at first - because Roborazzi has different modes you can activate:
  - `record` will create the (golden) files,
  - `compare` will compare with your golden files (and create diff images),
  - and `verify` will make the test fail when something is different.
- To capture, run: `./gradlew :app:recordRoborazziDebug`
  - or: add `roborazzi.test.record=true` to your `gradle.properties`, then run test
- Note that this is using the Espresso testing framework. Yes, you can use Espresso in your JVM unit tests!
- Robolectric comes with some nice qualifiers you can use, like `RobolectricDeviceQualifiers.Pixel4`

## ComposeActivityTest

Renders an activity that uses Jetpack Compose.

- all the finders, like `onRoot()`, work here as well!

## ComposeTest

Renders Composables. Yay Compose!

- no activity required - even cleaner code!
- the last test is using `captureRoboImage` from robolectric-compose - no Rule required!

## LottieTest

Renders a [Lottie](https://airbnb.design/lottie/) animation from unit tests - capturing multiple frames!

- set `images = 42` and see the generated images
- I used this to debug a Lottie animation that was set up with custom translations using [setTextDelegate](https://github.com/airbnb/lottie-android/blob/34aa06b8db75976f06b134b4fdcdb7dc26e48e07/lottie/src/main/java/com/airbnb/lottie/LottieAnimationView.java#L1008),
  to see that the translations were actually being rendered.

## Using Roborazzi to check your Compose upgrade

- Run `./gradlew :app:recordRoborazziDebug` to record
- Upgrade Compose from 2023.08.00 to 2023.10.00
- Run `./gradlew :app:verifyRoborazziDebug` to verify upgrade

## nowinandroid

To see more real-world examples using Robolectric & Roborazzi:

1. Open https://github.com/android/nowinandroid in Android Studio
2. Open `ForYouScreenScreenshotTests`
3. Run `./gradlew :feature:foryou:recordRoborazziDebug` to record the PNG files or `./gradlew :feature:foryou:compareRoborazziDebug`

## That's all folks!

Look at https://github.com/takahirom/roborazzi to see all the amazing other things it can do - like
capturing single views, advanced CI stuff, recording GIFs and more!

If you liked this talk, please follow and ping me on [Mastodon](https://mastodon.social/@mreichelt)
or [Twitter](https://twitter.com/mreichelt). Thank you!

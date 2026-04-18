package csci_712.main

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertTrue

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var device: UiDevice
    private val PACKAGE_NAME = "csci_712.main"

    @Before
    fun setUp() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Go to home screen
        device.pressHome()

        // Launch app from launcher
        val context = InstrumentationRegistry.getInstrumentation().context
        val intent = context.packageManager.getLaunchIntentForPackage(PACKAGE_NAME)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

        context.startActivity(intent)

        // Wait for app to open
        device.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), 5000)
    }

    @Test
    fun testNavigateToSecondActivity() {

        // Find button by text
        val button = device.findObject(UiSelector().text("Start Activity Explicitly"))

        // Click it
        button.click()

        // Wait for second activity
        device.wait(Until.hasObject(By.textContains("Mobile")), 3000)
        // Verify at least one challenge text exists
        val challenge = device.findObject(
            UiSelector().textContains("Device fragmentation")
        )

        assertTrue("Challenge text not found!", challenge.exists())
    }
}
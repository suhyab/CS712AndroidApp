package csci_712.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import android.os.Build
import android.content.IntentFilter
import android.content.Context
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                // Nothing special needed
            }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        myReceiver = MyBroadcastReceiver()

        val filter = IntentFilter("csci_712.main.MY_ACTION")
        registerReceiver(myReceiver, filter, Context.RECEIVER_NOT_EXPORTED)

        if (ContextCompat.checkSelfPermission(
                this,
                "csci_712.main.MSE712"
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionLauncher.launch("csci_712.main.MSE712")
        }

        setContent {
            MainScreen()
        }
    }

    private lateinit var myReceiver: MyBroadcastReceiver

    override fun onStop() {
        super.onStop()
        unregisterReceiver(myReceiver)
    }

    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->

            if (isGranted) {
                // Permission granted
            } else {
                // Permission denied
            }
        }

}

@Composable
fun MainScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(text = "Full Name: Suhyab Shaik", style = MaterialTheme.typography.titleLarge)
        Text(text = "Student ID: 1541827")

        Button(onClick = {
            val intent = Intent(context, SecondActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("Start Activity Explicitly")
        }

        Button(onClick = {
            val intent = Intent("csci_712.main.OPEN_SECOND_ACTIVITY")
            context.startActivity(intent)
        }) {
            Text("Start Activity Implicitly")
        }

        Button(onClick = {
            val intent = Intent(context, MyForegroundService::class.java)
            context.startForegroundService(intent)
        }) {
            Text("Start Service")
        }

        Button(onClick = {
            val intent = Intent("csci_712.main.MY_ACTION")
            intent.setPackage(context.packageName)
            context.sendBroadcast(intent)
        }) {
            Text("Send Broadcast")
        }

        Button(onClick = {
            val intent = Intent(context, ImageActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("View Image Activity")
        }

    }
}

package csci_712.main

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SecondScreen()
        }
    }
}

@Composable
fun SecondScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Mobile Software Engineering Challenges",
            style = MaterialTheme.typography.titleLarge
        )

        Text("1. Fragment lifecycle management")
        Text("2. Device fragmentation")
        Text("3. Battery optimization")
        Text("4. Security & permissions")
        Text("5. Backward compatibility")

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            (context as Activity).finish()
        }) {
            Text("Main Activity")
        }
    }
}


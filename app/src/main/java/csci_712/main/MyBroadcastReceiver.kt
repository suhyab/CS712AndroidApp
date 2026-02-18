package csci_712.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == "csci_712.main.MY_ACTION") {
            Toast.makeText(context, "Broadcast received!", Toast.LENGTH_SHORT).show()
        }
    }
}

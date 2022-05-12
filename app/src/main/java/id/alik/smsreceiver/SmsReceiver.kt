package id.alik.smsreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.gsm.SmsMessage
import android.widget.Toast

class SmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val extras = intent?.extras
        if (extras != null) {
            val sms = extras.get("pdus") as Array<*>
            for (i in sms.indices) {
                val format = extras.getString("format")

                val smsMessage = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    SmsMessage.createFromPdu(sms[i] as ByteArray)
                } else {
                    SmsMessage.createFromPdu(sms[i] as ByteArray)
                }

                val phoneNumber = smsMessage.originatingAddress
                val messageText = smsMessage.messageBody.toString()

                Toast.makeText(
                    context,
                    "Phone Number: $phoneNumber\n" + "MessageText: $messageText",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}

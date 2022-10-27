package iot.lab.qrdetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        var rollNumber: String = intent.getStringExtra("Roll Number").toString()
        val message = findViewById<TextView>(R.id.message)
        message.setText(rollNumber)
    }
}
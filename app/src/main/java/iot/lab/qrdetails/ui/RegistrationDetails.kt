package iot.lab.qrdetails.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import iot.lab.qrdetails.R

class RegistrationDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_details)

        val rollNumber = intent.getStringExtra("Roll Number").toString()



    }
}
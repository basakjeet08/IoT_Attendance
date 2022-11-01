package iot.lab.qrdetails.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import iot.lab.qrdetails.databinding.ActivityQrRollChoiceBinding

class QrRollChoiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQrRollChoiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrRollChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnQRScanner.setOnClickListener {
            val intent = Intent(this , QrScannerActivity::class.java)
            startActivity(intent)
        }
        binding.btnGetStatus.setOnClickListener {
            val roll = binding.etRollNumber.text.toString()

            if(roll.isNotEmpty()) {
                val intent = Intent(this, RegistrationDetails::class.java)
                intent.putExtra("ROLL_NUMBER", roll)
                startActivity(intent)
            }
            else
                Toast.makeText(this , "Enter Roll Number" , Toast.LENGTH_SHORT).show()
        }
    }
}
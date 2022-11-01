package iot.lab.qrdetails

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import iot.lab.qrdetails.databinding.ActivityQrRollChoiceBinding

class QrRollChoiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQrRollChoiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrRollChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnQRScanner.setOnClickListener {
            val intent = Intent(this , MainActivity::class.java)
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
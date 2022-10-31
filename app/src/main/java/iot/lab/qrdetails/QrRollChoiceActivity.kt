package iot.lab.qrdetails

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
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

        }
    }
}
package iot.lab.qrdetails

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import iot.lab.qrdetails.databinding.ActivityChoiceBinding

class ChoiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChoiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoiceBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.innovance.setOnClickListener {
            val intent = Intent(this@ChoiceActivity, QrRollChoiceActivity::class.java);
            startActivity(intent);
        }
        binding.attendance.setOnClickListener {
            val intent = Intent(this@ChoiceActivity, AttendanceActivity::class.java);
            startActivity(intent);
        }
    }
}
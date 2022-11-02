package iot.lab.qrdetails.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import iot.lab.qrdetails.R
import iot.lab.qrdetails.databinding.ActivityRegistrationDetailsBinding
import iot.lab.qrdetails.repository.Repository
import iot.lab.qrdetails.viewmodel.RegistrationDetailsViewModel
import iot.lab.qrdetails.viewmodel.RegistrationDetailsViewModelFactory

class RegistrationDetails : AppCompatActivity() {

    private lateinit var binding : ActivityRegistrationDetailsBinding
    private lateinit var viewModel: RegistrationDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rollNumber = intent.getStringExtra("ROLL_NUMBER").toString()
        val viewModelFactory = RegistrationDetailsViewModelFactory(Repository())
        viewModel = ViewModelProvider(this , viewModelFactory)[RegistrationDetailsViewModel::class.java]

        viewModel.myResponse.observe(this){ response ->
            if(response.isSuccessful){
                val data = response.body()!!.data[0]
                binding.tvName.text = getString(R.string.name , "${data.first_name} ${data.last_name}")
                binding.tvRoll.text = getString(R.string.roll_no , data.id)
                binding.tvPlanType.text = getString(R.string.plan_type , data.plan_type)
                binding.tvPlanDescription.text = getString(R.string.plan_description , data.plan_description)
                binding.tvFoodOpted.text = getString(R.string.food_opted_for , data.food_opted)
                binding.tvEmail.text = getString(R.string.email_s , data.email_personal)
                binding.tvContact.text = getString(R.string.contact_s , data.ph_number)
            }

        }
        viewModel.getRegistrationDetails(rollNumber)
    }
}
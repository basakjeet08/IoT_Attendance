package iot.lab.qrdetails.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import iot.lab.qrdetails.R
import iot.lab.qrdetails.databinding.ActivityRegistrationDetailsBinding
import iot.lab.qrdetails.model.RegistrationData
import iot.lab.qrdetails.repository.Repository
import iot.lab.qrdetails.viewmodel.RegistrationDetailsViewModel
import iot.lab.qrdetails.viewmodel.RegistrationDetailsViewModelFactory

class RegistrationDetails : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationDetailsBinding
    private lateinit var viewModel: RegistrationDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Getting the Roll Number From the previous Activity
        val rollNumber = intent.getStringExtra("ROLL_NUMBER").toString()

        //Setting Up the ViewModel for this activity
        val viewModelFactory = RegistrationDetailsViewModelFactory(Repository())
        viewModel = ViewModelProvider(this, viewModelFactory)[RegistrationDetailsViewModel::class.java]

        //Setting The Observer for the response variable which stores the response from the API Call
        viewModel.myResponse.observe(this) { response ->
            val data = response.body()!!.data
            if (data.isNotEmpty()) {
                //Calling the Function which will set all the textViews with data
                showRegistrationDetails(data[0])
            } else
                Toast.makeText(this, "No Details Found!!", Toast.LENGTH_LONG).show()
        }

        //Calling the functions to fetch the data From the API
        viewModel.getRegistrationDetails(rollNumber)
    }

    //This function sets all the details of the individual to the TextViews
    private fun showRegistrationDetails(data: RegistrationData) {
        binding.tvName.text = getString(R.string.name, "${data.first_name} ${data.last_name}")
        binding.tvRoll.text = getString(R.string.roll_no, data.id)
        binding.tvPlanType.text = getString(R.string.plan_type, data.plan_type)
        binding.tvPlanDescription.text = getString(R.string.plan_description, data.plan_description)

        //Setting the Fare Money and removing .00000 from the value
        val fare = data.total_fare!!.replace(".00000" , "")
        binding.tvTotalFare.text = getString(R.string.total_fare_s , fare)

        //Checking the food opted or not and displaying it properly
        if(data.food_opted == true)
            binding.tvFoodOpted.text = getString(R.string.food_opted_for, "Yes")
        else
            binding.tvFoodOpted.text = getString(R.string.food_opted_for , "No")

        binding.tvEmail.text = getString(R.string.email_s, data.email_personal)
        binding.tvContact.text = getString(R.string.contact_s, data.ph_number)
    }
}
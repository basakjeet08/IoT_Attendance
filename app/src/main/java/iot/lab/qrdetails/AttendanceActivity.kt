package iot.lab.qrdetails

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import iot.lab.qrdetails.databinding.ActivityAttendanceBinding
import iot.lab.qrdetails.repository.Repository
import java.util.*

class AttendanceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAttendanceBinding
    private lateinit var viewModel: MainViewModel
    private val adapter by lazy { RecordAdapter(this) }

    private var forFixedDay : String ? = null
    private var forFixedMonth : String ? = null
    private var forFixedYear : String ? = null
    private var inTimeBetweenStart : String? = null
    private var inTimeBetweenEnd : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAttendanceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupInstances()

        // Setup of the Observable data or the Live Data which will be executed when the fetched data changes
        viewModel.myResponse.observe(this){ response ->
            if(response.isSuccessful) {
                Log.d("Response", response.body().toString())
                adapter.updateList(response.body()!!.data)
            }
        }

        // Setting to Listener for the Button GET STATUS
        binding.getAttendance.setOnClickListener {
            controlFlow()
            setViewsToDefault()
        }
        binding.fromDateLinearLayout.setOnClickListener {
            setDatePicker(1)
        }
        binding.toDateLinearLayout.setOnClickListener {
            setDatePicker(2)
        }
    }

    //Sets all the Views to their Default Values
    private fun setViewsToDefault(){
        binding.rollNumber.text.clear()
        forFixedDay = null
        forFixedMonth = null
        forFixedYear = null
        inTimeBetweenStart = null
        inTimeBetweenEnd = null
        binding.tvFromDate.text = getString(R.string.from_date)
        binding.tvToDate.text = getString(R.string.to_date)
    }

    //setting up the RecyclerView and the ViewModel
    private fun setupInstances(){
        //Setting Up the RecyclerView Layout and the Adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        // Setting Up the ViewModel and making up the ViewModel Object
        val viewModelFactory = MainViewModelFactory(Repository())
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    //It contains all the logic and the API calls
    private fun controlFlow(){

        //Getting the roll from the user and storing it
        val rollNumberText = binding.rollNumber.text.toString()

        //Checking if the EditText field is empty or not
        if(binding.rollNumber.text.isEmpty())
            Toast.makeText(this , "Invalid Roll Number " , Toast.LENGTH_SHORT).show()
        else {

            //Checking which API to call and calling them
            if(inTimeBetweenStart == null && inTimeBetweenEnd == null)
                viewModel.getPost(rollNumberText)
            else if(inTimeBetweenStart == inTimeBetweenEnd )
                viewModel.getPostOfFixedDay(rollNumberText , forFixedDay!! , forFixedMonth!! , forFixedYear!!)
            else if(inTimeBetweenStart != null && inTimeBetweenEnd != null)
                viewModel.getPostBetweenDays(rollNumberText ,"$inTimeBetweenStart,$inTimeBetweenEnd")
        }
    }

    // Setting up the Date Picker and taking the Dates as follows
    private fun setDatePicker(flagValue : Int){

        val myCalendar = Calendar.getInstance()
        val cal = DatePickerDialog(this , { _, selectedYear, selectedMonth, selectedDay ->
            when(flagValue){
                1 -> {
                    forFixedDay = selectedDay.toString()
                    forFixedMonth = (selectedMonth+1).toString()
                    forFixedYear = selectedYear.toString()
                    inTimeBetweenStart = "$selectedYear-${selectedMonth+1}-$selectedDay"
                    val formatToShow = "$selectedDay-${selectedMonth+1}-$selectedYear"
                    binding.tvFromDate.text = getString(R.string.format , formatToShow)
                }
                2 -> {
                    inTimeBetweenEnd = "$selectedYear-${selectedMonth+1}-$selectedDay"
                    val formatToShow = "$selectedDay-${selectedMonth+1}-$selectedYear"
                    binding.tvToDate.text = getString(R.string.format , formatToShow)
                }
            }
        }, myCalendar.get(Calendar.YEAR) , myCalendar.get(Calendar.MONTH) , myCalendar.get(Calendar.DATE))
        cal.datePicker.maxDate = System.currentTimeMillis()
        cal.show()
    }
}
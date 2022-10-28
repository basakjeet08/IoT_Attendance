package iot.lab.qrdetails

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import iot.lab.qrdetails.databinding.ActivityAttendanceBinding
import iot.lab.qrdetails.repository.Repository
import java.util.*

class AttendanceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAttendanceBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private val adapter by lazy { recordAdapter() }




    private var inTimeBetweenStart : String? = null
    private var inTimeBetweenEnd : String? = null






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAttendanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Setting Up the RecyclerView Layout and the Adapter
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)


        // Setting Up the ViewModel and making up the ViewModel Object
        val viewModelFactory = MainViewModelFactory(Repository())
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        // Setup of the Observable data or the Live Data which will be executed when the fetched data changes
        viewModel.myResponse.observe(this){ response ->
            if(response.isSuccessful) {
                Log.d("Response", response.body().toString())
                adapter.updateList(response.body()!!.data)
            }
        }

        // Setting to Listener for the Button GET STATUS
        binding.getAttendance.setOnClickListener {
            if(binding.rollNumber.text.isEmpty())
                Toast.makeText(this , "Enter the Roll Number " , Toast.LENGTH_SHORT).show()
            else {
                if(inTimeBetweenStart == null && inTimeBetweenEnd == null)
                    showPostByRollNumber()
                else if(inTimeBetweenStart == inTimeBetweenEnd )
                    showPostOfFixedDay()
                else
                    showPostBetweenDay()
            }
        }

        binding.btnFromDate.setOnClickListener {
            setDatePicker(1)
        }

        binding.btnFromDate.setOnClickListener {
            setDatePicker(2)
        }
    }

    // Shows the fetched Data with The Roll API call only
    private fun showPostByRollNumber(){
        val rollNumberText = binding.rollNumber.text.toString()
        viewModel.getPost(rollNumberText)
        binding.rollNumber.text.clear()
    }

    //Shows the fetched Data of a Roll at fixed Day
    //TODO not completed
    private fun showPostOfFixedDay(){
        val rollNumberText = binding.rollNumber.text.toString()
        viewModel.getPostOfFixedDay(rollNumberText , "18" , "10" , "2022")
        binding.rollNumber.text.clear()
    }

    //Shows the fetched Data of a roll in a Range of dates
    private fun showPostBetweenDay(){
        val rollNumberText = binding.rollNumber.text.toString()
        viewModel.getPostBetweenDays(rollNumberText ,"$inTimeBetweenStart,$inTimeBetweenEnd")
        binding.rollNumber.text.clear()
    }

    // Setting up the Date Picker and taking the Dates as follows
    private fun setDatePicker(value : Int){
        val myCalendar = Calendar.getInstance()
        val currentYear = myCalendar.get(Calendar.YEAR)
        val currentMonth = myCalendar.get(Calendar.MONTH)
        val currentDate = myCalendar.get(Calendar.DATE)
        DatePickerDialog(this , { _, selectedYear, selectedMonth, selectedDay ->
            when(value){
                1 -> inTimeBetweenStart = "$selectedYear-$selectedMonth-$selectedDay"
                2 -> inTimeBetweenEnd = "$selectedYear-$selectedMonth-$selectedDay"
            }
        }, currentYear , currentMonth , currentDate).show()
    }
}
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




    private var inTimeBetween : String? = null
    private var fixedTimeYear : String? = null
    private var fixedTimeMonth : String? = null
    private var fixedTimeDate : String? = null






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
                setDatePicker(1)
                if(inTimeBetween == null && fixedTimeDate == null)
                    showPostByRollNumber()
                else if(inTimeBetween == null)
                    showPostOfFixedDay()
                else
                    showPostBetweenDay()
            }
        }
    }

    // Shows the fetched Data with The Roll API call only
    private fun showPostByRollNumber(){
        val rollNumberText = binding.rollNumber.text.toString()
        viewModel.getPost(rollNumberText)
        binding.rollNumber.text.clear()
    }

    //Shows the fetched Data of a Roll at fixed Day
    private fun showPostOfFixedDay(){
        val rollNumberText = binding.rollNumber.text.toString()
        viewModel.getPostOfFixedDay(rollNumberText , "18" , "10" , "2022")
        binding.rollNumber.text.clear()
    }

    //Shows the fetched Data of a roll in a Range of dates
    private fun showPostBetweenDay(){
        val rollNumberText = binding.rollNumber.text.toString()
        viewModel.getPostBetweenDays(rollNumberText ,"2022-09-10,2022-10-27")
        binding.rollNumber.text.clear()
    }

    private fun setDatePicker(value : Int){
        val myCalendar = Calendar.getInstance()
        val currentYear = myCalendar.get(Calendar.YEAR)
        val currentMonth = myCalendar.get(Calendar.MONTH)
        val currentDate = myCalendar.get(Calendar.DATE)
        DatePickerDialog(this , { _, selectedYear, selectedMonth, selectedDay ->
            when(value){
                1 -> {
                    fixedTimeDate = selectedDay.toString()
                    fixedTimeMonth = selectedMonth.toString()
                    fixedTimeYear = selectedYear.toString()
                }
                2 -> inTimeBetween = "$selectedYear-$selectedMonth-$selectedDay,"
                3 -> inTimeBetween += "$selectedYear-$selectedMonth-$selectedDay"
            }
        }, currentYear , currentMonth , currentDate).show()
    }
}
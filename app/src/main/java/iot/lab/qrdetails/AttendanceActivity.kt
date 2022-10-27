package iot.lab.qrdetails

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import iot.lab.qrdetails.databinding.ActivityAttendanceBinding
import iot.lab.qrdetails.repository.Repository

class AttendanceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAttendanceBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private val adapter by lazy { recordAdapter() }

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
                showPostByRollNumber()
//                showPostOfFixedDay()
//                showPostBetweenDay()
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
}
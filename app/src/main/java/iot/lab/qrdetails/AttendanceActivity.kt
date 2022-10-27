package iot.lab.qrdetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import iot.lab.qrdetails.databinding.ActivityAttendanceBinding
import iot.lab.qrdetails.databinding.ActivityChoiceBinding
import iot.lab.qrdetails.model.Post
import iot.lab.qrdetails.repository.Repository

class AttendanceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAttendanceBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private val repository = Repository()
    private val adapter by lazy { recordAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAttendanceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()


        binding.getAttendance.setOnClickListener {

            if(binding.rollNumber.text.isEmpty())
                Toast.makeText(this , "Enter the Roll Number " , Toast.LENGTH_SHORT).show()
            else
                showPostByRollNumber()
        }


    }
    private fun showPostByRollNumber(){

        val rollNumberText = binding.rollNumber.text

        val repository =  Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getPost(rollNumberText.toString())
        viewModel.myResponse.observe(this, Observer { response ->
            if(response.isSuccessful) {

                Log.d("Response", response.body().toString())


                recyclerView.adapter = recordAdapter()


            }
        })
        binding.rollNumber.text.clear()
    }
    private fun showPostOfFixedDay(){
        val rollNumberText = binding.rollNumber.text
        Log.d("Response", "heyyyyyyyyyyyyyy")
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val repository =  Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

//        viewModel.getPost(rollNumberText.toString())
        viewModel.getPostOfFixedDay(rollNumberText.toString() , "18" , "10" , "2022")
//        viewModel.getPostBetweenDays(rollNumberText.toString() ,"2022-09-10,2022-09-12")


        viewModel.myResponse.observe(this, Observer { response ->
            if(response.isSuccessful) {

                Log.d("Response", response.body().toString())
                recyclerView.adapter = recordAdapter(response.body()?.data as ArrayList<Post>)
            }
        })
        binding.rollNumber.text.clear()
    }

    private fun showPostBetweenDay(){
        val rollNumberText = binding.rollNumber.text
        Log.d("Response", "heyyyyyyyyyyyyyy")
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val repository =  Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getPostBetweenDays(rollNumberText.toString() ,"2022-09-10,2022-10-27")


        viewModel.myResponse.observe(this, Observer { response ->
            if(response.isSuccessful) {
                Log.d("Response", response.body().toString())
                recyclerView.adapter = recordAdapter(response.body()?.data as ArrayList<Post>)
            }
        })
        binding.rollNumber.text.clear()
    }

    fun setupRecyclerView(){
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
    }
}
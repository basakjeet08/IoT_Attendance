package iot.lab.qrdetails.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import iot.lab.qrdetails.R
import iot.lab.qrdetails.model.Post
import java.text.SimpleDateFormat
import java.util.*

class AttendanceAdapter(private val context: Context) : RecyclerView.Adapter<AttendanceAdapter.MyViewHolder>() {
    private var data : List<Post> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_attendance_row, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = data[position]
        val currentInTime : String? = currentItem.in_time
        val currentOutTime : String? = currentItem.out_time


        //Format which we received From the API call
        val formatReceived = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SS.sss'Z'")

        //Setting TimeZone to Indian which is GMT_5.30
        formatReceived.timeZone = TimeZone.getTimeZone("GMT+5.30")

        //Setting Our Desired Format of Date and Time
        val desiredFormat = SimpleDateFormat("dd-MMM-yyyy      'Time : ' hh:mm:ss a")

        //Making Date objects from the in time and out Time strings
        val dateInTime = currentInTime?.let { formatReceived.parse(it) }
        val dateOutTime = currentOutTime?.let { formatReceived.parse(it) }

        // Parsing the date Objects which returns the String in the format as specified in desiredFormat variable
        val correctedInTime = dateInTime?.let { desiredFormat.format(it) }
        val correctedOutTime = dateOutTime?.let { desiredFormat.format(it) }

        //Setting up the data to the Views
        holder.roll.text = context.getString(R.string.roll_no, currentItem.roll)
        holder.inTime.text = context.getString(R.string.in_time, correctedInTime)
        if(correctedOutTime == null)
            holder.outTime.text = context.getString(R.string.out_time, "None")
        else
            holder.outTime.text = context.getString(R.string.out_time, correctedOutTime)


    }

    override fun getItemCount(): Int {
        return data.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val roll: TextView = itemView.findViewById(R.id.roll)
        val inTime: TextView = itemView.findViewById(R.id.in_time)
        val outTime: TextView = itemView.findViewById(R.id.out_time)

    }
    fun updateList(newList: List<Post>) {
        data = newList.sortedByDescending {
            it.in_time
        }
        notifyDataSetChanged()
    }
}

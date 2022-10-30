package iot.lab.qrdetails

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import iot.lab.qrdetails.model.Post
import java.text.SimpleDateFormat
import java.util.*

class RecordAdapter(private val context: Context) : RecyclerView.Adapter<RecordAdapter.MyViewHolder>() {
    private var data : List<Post> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.records_row, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = data[position]

        //Format which we received From the API call
        val formatReceived = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SS.sss'Z'")

        //Setting TimeZone to Indian which is GMT_5.30
        formatReceived.timeZone = TimeZone.getTimeZone("GMT+5.30")

        //Setting Our Desired Format of Date and Time
        val desiredFormat = SimpleDateFormat("dd-MMM-yyyy      'Time : ' hh:mm:ss a")

        //Making Date objects from the in time and out Time strings
        val dateInTime = formatReceived.parse(currentItem.in_time)!!
        val dateOutTime = formatReceived.parse(currentItem.out_time)!!

        // Parsing the date Objects which returns the String in the format as specified in desiredFormat variable
        val correctedInTime = desiredFormat.format(dateInTime)
        val correctedOutTime = desiredFormat.format(dateOutTime)

        //Setting up the data to the Views
        holder.roll.text = context.getString(R.string.roll_no , currentItem.roll)
        holder.inTime.text = context.getString(R.string.in_time , correctedInTime)
        holder.outTime.text = context.getString(R.string.out_time , correctedOutTime)


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

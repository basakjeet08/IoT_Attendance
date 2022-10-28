package iot.lab.qrdetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import iot.lab.qrdetails.model.Post

class RecordAdapter(private val context: Context) : RecyclerView.Adapter<RecordAdapter.MyViewHolder>() {
    private var data : List<Post> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.records_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = data[position]

        val correctedInTime = currentItem.in_time.removeRange(19 , 24).replace("T" , " \tTime : ")
        val correctedOutTime = currentItem.out_time.removeRange(19,24).replace("T" , " \tTime : ")
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
        data = newList
        notifyDataSetChanged()
    }
}

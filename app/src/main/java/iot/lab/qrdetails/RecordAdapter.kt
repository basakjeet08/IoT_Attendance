package iot.lab.qrdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import iot.lab.qrdetails.model.Post

class RecordAdapter() : RecyclerView.Adapter<RecordAdapter.MyViewHolder>() {
    private var data : List<Post> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordAdapter.MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.record, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecordAdapter.MyViewHolder, position: Int) {
        val currentItem = data[position]
        holder.roll.text = currentItem.roll
        holder.in_time.text = currentItem.in_time
        holder.out_time.text = currentItem.out_time


    }

    override fun getItemCount(): Int {
        return data.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val roll: TextView = itemView.findViewById(R.id.roll)
        val in_time: TextView = itemView.findViewById(R.id.in_time)
        val out_time: TextView = itemView.findViewById(R.id.out_time)

    }
    fun updateList(newList: List<Post>) {
        data = newList
        notifyDataSetChanged()
    }
}

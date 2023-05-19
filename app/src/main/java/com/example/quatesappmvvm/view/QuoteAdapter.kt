package com.example.quatesappmvvm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quatesappmvvm.R
import com.example.quatesappmvvm.service.model.QuoteList
import com.example.quatesappmvvm.service.model.Result

class QuoteAdapter: ListAdapter<Result,QuoteAdapter.QuoteViewHolder > (DiffUtilCallBack())
{
    class QuoteViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val name=view.findViewById<TextView>(R.id.name)
        val initial=view.findViewById<TextView>(R.id.initial)

      /*  fun bind(item: QuoteList)
        {
            name.text=item.results.get(0).content
            initial.text=item.initial
        }*/

    }

    class DiffUtilCallBack:androidx.recyclerview.widget.DiffUtil.ItemCallback<Result>(){
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem._id==newItem._id
    }

    override fun areContentsTheSame(
        oldItem: Result,
        newItem: Result
    ): Boolean {
        return oldItem==newItem
    }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuoteAdapter.QuoteViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return  QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteAdapter.QuoteViewHolder, position: Int) {
        val item=getItem(position)
       // holder.bind(item)
        holder.name.text=item.content
        holder.initial.text=item.author
    }

}
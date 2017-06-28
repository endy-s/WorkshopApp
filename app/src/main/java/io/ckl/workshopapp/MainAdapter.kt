package io.ckl.workshopapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_main.view.*

/**
 * Created by endysilveira on 22/06/17.
 */

class MainAdapter(val listener: MainItemListener): RecyclerView.Adapter<MainViewHolder>() {

    var contentList: List<String> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_main, parent, false)
        return MainViewHolder(view)

    }

    override fun onBindViewHolder(holder: MainViewHolder?, position: Int) {
        holder?.onBind(content = contentList[position], listener = listener)
    }

    override fun getItemCount(): Int {
        return contentList.size
    }

}

class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {

    fun onBind(content: String, listener: MainItemListener) {
        itemView.textView.text = content
        itemView.setOnClickListener { listener.onItemClicked(content) }
    }
}

interface MainItemListener {

    fun onItemClicked(content: String)
}
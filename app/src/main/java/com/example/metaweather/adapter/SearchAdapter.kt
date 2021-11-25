package com.example.metaweather.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.location_item.view.*
import com.example.metaweather.R
import com.example.metaweather.model.Location
import com.example.metaweather.view.DetailsActivity

class SearchAdapter(var context: Context) : //view와 data를 연결
    RecyclerView.Adapter<SearchAdapter.SearchAdapterViewHolder>() {
    private var list: List<Location> = ArrayList()

    fun setLocation(list: List<Location>) {
        this.list = list
        notifyDataSetChanged() //갱신처리 => 어댑터에게 뷰가 갱신되었음을 알려줌
    }

    inner class SearchAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapterViewHolder { //onCreateViewHolder랑 onBindViewHolder 반드시 상속
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.location_item, parent, false) //LayoutInflater => XML에 정의된 Resource를 메모리에 올려서 View 객체로 반환
        return SearchAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchAdapterViewHolder, position: Int) { //findViewById를 매번 호출X => ItemView의 각 요소에 바로 엑세스
        val currLocation = list[position]
        holder.itemView.locationNameTextView.text = currLocation.title
        holder.itemView.root_view.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("Location", currLocation.woeid)
            intent.putExtra("name", currLocation.title)
            context.startActivity(intent)
        }
    }
}
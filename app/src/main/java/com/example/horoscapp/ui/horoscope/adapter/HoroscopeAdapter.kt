package com.example.horoscapp.ui.horoscope.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscapp.R
import com.example.horoscapp.domain.model.HoroscopeInfo

class HoroscopeAdapter(private var horoscope: List<HoroscopeInfo> = emptyList(),
    private val onItemSelected:(HoroscopeInfo) -> Unit) :
    RecyclerView.Adapter<HoroscopeViewHolder>() {

    fun updateList(list:List<HoroscopeInfo>){
        horoscope = list
        //Voy a cambiar el listado completo, por eso llamar al notify es la mejor manera de hacerlo.
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        return HoroscopeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false)
        )
    }

    override fun getItemCount(): Int = horoscope.size

    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        holder.render(horoscope[position],onItemSelected)

    }


}
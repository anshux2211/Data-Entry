package com.example.dataentry

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class contact_list_adapter(
    private val listOfContacts:List<contact_data>
):RecyclerView.Adapter<contact_list_adapter.ContactListViewHolder>() {
    class ContactListViewHolder(view:View):RecyclerView.ViewHolder(view){
        val img:ImageView=view.findViewById(R.id.image)
        val name:TextView=view.findViewById(R.id.name)
        val phone:TextView=view.findViewById(R.id.phone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.layout_rv_item,parent,false)
        return ContactListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfContacts.size
    }

    override fun onBindViewHolder(holder: ContactListViewHolder, position: Int) {
        holder.img.setImageURI(listOfContacts[position].imageres)
        holder.name.text=listOfContacts[position].curr_name
        holder.phone.text=listOfContacts[position].curr_phone
    }
}
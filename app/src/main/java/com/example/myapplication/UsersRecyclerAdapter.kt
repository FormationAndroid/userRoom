package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.database.User
import kotlinx.android.synthetic.main.item_user.view.*

class UsersRecyclerAdapter(
    var users: MutableList<User>
): RecyclerView.Adapter<UsersRecyclerAdapter.UsersViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UsersViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(users[position])
    }

    inner class UsersViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_user, parent, false)){

        fun bind(user: User){
            itemView.apply {
                textName.text = user.name
            }
        }
    }

    override fun getItemCount() = users.size

}
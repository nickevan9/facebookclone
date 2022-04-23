package com.example.facebookclone.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.facebookclone.R
import com.example.facebookclone.model.User
import com.example.facebookclone.model.UserSaved

class ProfileUserAdapter(val context: Context, var listUser: List<UserSaved>?,val callback : (UserSaved) -> Unit) :
    RecyclerView.Adapter<ProfileUserAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItem(user = listUser!![position])
    }

    override fun getItemCount(): Int = listUser?.size!!

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tv_name)
        private val imgAvatar: ImageView = itemView.findViewById(R.id.img_avatar)

        @SuppressLint("SetTextI18n")
        fun bindItem(user: UserSaved) {
            tvName.text = user.firstName + " " + user.lastName

            Glide
                .with(context)
                .load(user.photoUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_user_thumbnail)
                .into(imgAvatar);

            itemView.setOnClickListener {
                callback.invoke(user)
            }
        }
    }
}
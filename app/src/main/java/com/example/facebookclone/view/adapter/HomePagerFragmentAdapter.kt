package com.example.facebookclone.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.facebookclone.view.join.joinfriend.FriendRequestFragment
import com.example.facebookclone.view.join.joinhome.HomeFragment
import com.example.facebookclone.view.join.joinmenu.MenuFragment
import com.example.facebookclone.view.join.joinnotification.NotificationsFragment
import com.example.facebookclone.view.join.joinpersonal.PersonalProfileFragment
import com.example.facebookclone.view.join.joinwatch.WatchVideoFragment

class HomePagerFragmentAdapter(val arrayTitle : Array<String>,val fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
        return arrayTitle.size
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return HomeFragment()
            1 -> return FriendRequestFragment()
            2 -> return PersonalProfileFragment()
            3 -> return WatchVideoFragment()
            4 -> return NotificationsFragment()
            5 -> return MenuFragment()
        }
        return HomeFragment()
    }

}
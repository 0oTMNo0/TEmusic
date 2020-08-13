package com.example.temusic.Ui.Activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.temusic.R
import com.example.temusic.Ui.ui.fragments.AlbumsFragment
import com.example.temusic.Ui.ui.fragments.MusicFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private var bottomnavigation:BottomNavigationView? = null
    var manager:FragmentManager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showalbumFragment()
        bottomnavigation = findViewById(R.id.btn_nav)
        bottomnavigation!!.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.nav_music->showmusicFragment()
                R.id.nav_allbom->showalbumFragment()
            }
            return@setOnNavigationItemSelectedListener true
        }

    }
     private fun showmusicFragment(){
        val transactin = manager.beginTransaction()
        val fragment = MusicFragment()
        transactin.replace(R.id.palce_holder,fragment)
        transactin.addToBackStack(null)
        transactin.commit()
    }
     private fun showalbumFragment(){
        val transactin = manager.beginTransaction()
        val fragment = AlbumsFragment()
        transactin.replace(R.id.palce_holder,fragment)
        transactin.addToBackStack(null)
        transactin.commit()
    }
}
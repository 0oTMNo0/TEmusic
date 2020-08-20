package com.example.temusic.Ui.Activitys

import android.Manifest
import android.app.DownloadManager
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import com.example.temusic.R
import com.example.temusic.Ui.ui.fragments.AlbumsFragment
import com.example.temusic.Ui.ui.fragments.MusicFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var bottomnavigation:BottomNavigationView? = null
    var manager:FragmentManager = supportFragmentManager
lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /*
        if(!checkPermissionFromDevice())
            requestPermissions()

         */
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
        {
            val array = Array<String>(1){ Manifest.permission.READ_EXTERNAL_STORAGE}
            ActivityCompat.requestPermissions(this,array,2000)
        }


        showmusicFragment()
        bottomnavigation = findViewById(R.id.btn_nav)
        bottomnavigation!!.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_music -> showmusicFragment()
                R.id.nav_allbom -> showalbumFragment()
            }
            return@setOnNavigationItemSelectedListener true
        }
        ///////////////////////////////drawer
        setSupportActionBar(findViewById(R.id.toolbar_n))
        toggle= ActionBarDrawerToggle(this, drawer_layout ,R.string.drawer_open,R.string.drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nav_view.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.na_share -> Toast.makeText(applicationContext, "share", Toast.LENGTH_SHORT).show()
                R.id.na_rate -> Toast.makeText(applicationContext, "rate", Toast.LENGTH_SHORT).show()
                R.id.na_aboutus -> Toast.makeText(applicationContext, "about us", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
        {return true}
        return super.onOptionsItemSelected(item)
    }
    ////////////////////////////
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
    /*
    private fun checkPermissionFromDevice():Boolean{
        val writeExternalStorage=ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)
        return writeExternalStorage==PackageManager.PERMISSION_GRANTED
    }
    private fun requestPermissions(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),2)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode)
        {
            2 -> if (grantResults!!.size>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(applicationContext,"Permission grant...",Toast.LENGTH_LONG).show()
            }
        }
    }*/
}

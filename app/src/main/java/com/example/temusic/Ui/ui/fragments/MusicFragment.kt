package com.example.temusic.Ui.ui.fragments

import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.temusic.R
import com.example.temusic.Ui.SongInfo
import kotlinx.android.synthetic.main.fragment_music.*
import kotlinx.android.synthetic.main.shape_music.*
import kotlinx.android.synthetic.main.shape_music.view.*


class MusicFragment : Fragment(){

    var listofsong = ArrayList<SongInfo>()
    var adapter:myadapter?=null

    var mediaPlayer : MediaPlayer = MediaPlayer()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_music,container,false)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LoadSongsFromLocal()
        adapter = myadapter(listofsong)
        list_music.adapter=adapter
    }
    inner class myadapter : BaseAdapter
    {
        var mylist = ArrayList<SongInfo>()

        constructor(mylist:ArrayList<SongInfo>)
        {
            this.mylist=mylist
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var myview = layoutInflater.inflate(R.layout.shape_music,null)
            var song = mylist[p0]
            myview.txtview_title.setText(song.Title)
            myview.txtviewdesc.setText(song.Desc)
            myview.buttonPlay.setOnClickListener{
                if(myview.buttonPlay.text.equals("Stop"))
                {
                    mediaPlayer!!.stop()
                    myview.buttonPlay.text="Play"

                }
                else
                {
                    mediaPlayer.reset()
                    mediaPlayer!!.setDataSource(song.SongUrl)
                    mediaPlayer!!.prepare()
                    mediaPlayer!!.start()
                    myview.buttonPlay.text="Stop"

                }

            }
            return myview
        }

        override fun getItem(p0: Int): Any {
            return mylist[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return mylist.size
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun LoadSongsFromLocal()
    {
        val allsongs = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val selection = MediaStore.Audio.Media.IS_MUSIC+"!=0"



        val cursor = context?.contentResolver?.query(allsongs,null,selection,null,null)
        if(cursor != null)
        {
            if (cursor.moveToFirst() == true)
            {
                do{
                    val songurl = cursor!!.getString(cursor!!.getColumnIndex(MediaStore.Audio.Media.DATA))
                    val songAuthor = cursor!!.getString(cursor!!.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                    val songName = cursor!!.getString(cursor!!.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
                    listofsong.add(SongInfo(songName,songAuthor,songurl))

                }while (cursor.moveToNext() == true)
            }
        }
        cursor!!.close()
    }
}
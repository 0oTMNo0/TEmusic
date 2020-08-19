package com.example.temusic.Ui

class SongInfo
{
    var Title:String? = null
    var Desc:String? = null
    var SongUrl:String? = null
    constructor(title:String,desc:String,songUrl:String){
        this.Title=title
        this.Desc=desc
        this.SongUrl=songUrl
    }
}
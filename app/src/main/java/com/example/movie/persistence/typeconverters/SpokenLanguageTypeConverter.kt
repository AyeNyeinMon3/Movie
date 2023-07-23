package com.example.movie.persistence.typeconverters

import androidx.room.TypeConverter
import com.example.movie.data.vos.SpokenLanguageVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SpokenLanguageTypeConverter {

    @TypeConverter
    fun toString(spokenLanguageList: List<SpokenLanguageVO>?):String{
        return Gson().toJson(spokenLanguageList)
    }

    @TypeConverter
    fun toSpokenLanguageList(spokenLanguageJsonString: String):List<SpokenLanguageVO>?{
        val spokenLanguageList = object : TypeToken<List<SpokenLanguageVO>?>(){}.type
        return Gson().fromJson(spokenLanguageJsonString,spokenLanguageList)
    }

}
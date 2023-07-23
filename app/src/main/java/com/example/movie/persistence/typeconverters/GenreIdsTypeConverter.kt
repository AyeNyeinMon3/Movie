package com.example.movie.persistence.typeconverters

import androidx.room.TypeConverter
import com.example.movie.data.vos.GenreVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdsTypeConverter {

   @TypeConverter
   fun toString(genreList : List<Int>?) : String{
       return Gson().toJson(genreList)
   }

    @TypeConverter
    fun toGenreIds(genreIdJsonString: String) : List<Int>?{
        val genreIdsType = object : TypeToken<List<Int>?>() {}.type
        return Gson().fromJson(genreIdJsonString,genreIdsType)
    }
}
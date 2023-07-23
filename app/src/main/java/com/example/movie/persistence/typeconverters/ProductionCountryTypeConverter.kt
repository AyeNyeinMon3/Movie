package com.example.movie.persistence.typeconverters

import androidx.room.TypeConverter
import com.example.movie.data.vos.ProductionCountriesVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductionCountryTypeConverter {

    @TypeConverter
    fun toString(productionCountryList: List<ProductionCountriesVO>?):String{
        return Gson().toJson(productionCountryList)
    }

    @TypeConverter
    fun toProductionCountryList(productionCountryJsonString: String):List<ProductionCountriesVO>?{
        val productionCountryList = object :TypeToken<List<ProductionCountriesVO>?>(){}.type
        return Gson().fromJson(productionCountryJsonString,productionCountryList)
    }
}
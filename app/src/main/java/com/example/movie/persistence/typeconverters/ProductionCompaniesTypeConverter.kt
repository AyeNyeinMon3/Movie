package com.example.movie.persistence.typeconverters

import androidx.room.TypeConverter
import com.example.movie.data.vos.ProductionCompaniesVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductionCompaniesTypeConverter {

    @TypeConverter
    fun toString(productionCompaniesList: List<ProductionCompaniesVO>?):String{
        return Gson().toJson(productionCompaniesList)
    }

    @TypeConverter
    fun toProductionCompaniesList(productionCompaniesJsonString: String):List<ProductionCompaniesVO>?{
        val productionCompaniesList = object : TypeToken<List<ProductionCompaniesVO>?>(){}.type
        return Gson().fromJson(productionCompaniesJsonString,productionCompaniesList)
    }
}
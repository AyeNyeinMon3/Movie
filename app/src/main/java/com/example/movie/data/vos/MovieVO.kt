package com.example.movie.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.movie.persistence.typeconverters.GenreIdsTypeConverter
import com.example.movie.persistence.typeconverters.GenreListTypeConverter
import com.example.movie.persistence.typeconverters.ProductionCompaniesTypeConverter
import com.example.movie.persistence.typeconverters.ProductionCountryTypeConverter
import com.example.movie.persistence.typeconverters.SpokenLanguageTypeConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
@TypeConverters(
    GenreIdsTypeConverter::class,
    GenreListTypeConverter::class,
    ProductionCompaniesTypeConverter::class,
    ProductionCountryTypeConverter::class,
    SpokenLanguageTypeConverter::class
)


data class MovieVO(
    @SerializedName("adult")
    @ColumnInfo("adult")
    val adult : Boolean?,

    @SerializedName("backdrop_path")
    @ColumnInfo("backdrop_path")
    val backDropPack : String?,

//    @SerializedName("belongs_to_collection")
//    val belongsToCollection :

    @SerializedName("budget")
    @ColumnInfo("budget")
    val budget : Int?,

    @SerializedName("genres")
    @ColumnInfo("genres")
    val genres : List<GenreVO>?,

    @SerializedName("homepage")
    @ColumnInfo(name = "homepage")
    val homePage : String?,

    @SerializedName("genre_ids")
    val genreIds : List<Int>?,

    @SerializedName("id")
    @ColumnInfo("id")
    @PrimaryKey
    val id : Int = 0,

    @SerializedName("imdb_id")
    @ColumnInfo("imdb_id")
    val imdbId : String?,

    @SerializedName("original_language")
    @ColumnInfo("original_language")
    val originalLanguage : String?,

    @SerializedName("original_title")
    @ColumnInfo("original_title")
    val originalTitle : String?,

    @SerializedName("overview")
    @ColumnInfo("overview")
    val overview : String?,

    @SerializedName("popularity")
    @ColumnInfo("popularity")
    val popularity : Double?,

    @SerializedName("poster_path")
    @ColumnInfo("poster_path")
    val posterPath : String?,

    @SerializedName("production_companies")
    @ColumnInfo("production_companies")
    val productionCompanies : List<ProductionCompaniesVO>?,

    @SerializedName("production_countries")
    @ColumnInfo("production_countries")
    val productionCountries : List<ProductionCountriesVO>?,

    @SerializedName("release_date")
    @ColumnInfo("release_date")
    val releaseDate : String?,

    @SerializedName("revenue")
    @ColumnInfo("revenue")
    val revenue : Int?,

    @SerializedName("runtime")
    @ColumnInfo("runtime")
    val runTime : Int?,

    @SerializedName("spoken_languages")
    @ColumnInfo("spoken_languages")
    val spokenLanguage : List<SpokenLanguageVO>?,

    @SerializedName("status")
    @ColumnInfo("status")
    val status : String?,

    @SerializedName("tagline")
    @ColumnInfo("tagline")
    val tagLine : String?,

    @SerializedName("title")
    @ColumnInfo("title")
    val title : String?,

    @SerializedName("video")
    @ColumnInfo("video")
    val video : Boolean?,

    @SerializedName("vote_average")
    @ColumnInfo("vote_average")
    val voteAverage : Double?,

    @SerializedName("vote_count")
    @ColumnInfo("vote_count")
    val voteCount : Int?,

    //for movie type in persistence
    @ColumnInfo("type")
    var type : String?




){
    fun getRatingBaseOnFiveStars (): Float{
        return voteAverage?.div(2)?.toFloat() ?: 0.0f
    }

    fun getGenresAsCommaSeparatedString() : String{
        return genres?.map { it.name } ?.joinToString(", ") ?: ""
    }

    fun getProductionCompaniesAsCommaSeparatedString() : String{
        return productionCompanies?.map { it.name }?.joinToString(", ") ?: ""
    }



}

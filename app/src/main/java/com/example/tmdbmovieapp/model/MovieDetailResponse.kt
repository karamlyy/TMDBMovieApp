import com.example.tmdbmovieapp.model.ProductionCompany
import com.example.tmdbmovieapp.model.SpokenLanguage
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany?>?,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage?>?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
)
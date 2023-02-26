package ani.saikou.media

import java.io.Serializable

data class Media(
    val id: Int,

    var idMAL: Int? = null,
    var typeMAL: String? = null,

    val name: String?,
    val nameRomaji: String,

    var isAdult: Boolean,
    var format: String? = null,
    var countryOfOrigin: String? = null,
    var genres: ArrayList<String> = arrayListOf(),
    var synonyms: ArrayList<String> = arrayListOf(),

    var vrvId: String? = null,
    var crunchySlug: String? = null,

    var nameMAL: String? = null,
) : Serializable {

    fun mainName() = nameMAL ?: name ?: nameRomaji
    fun mangaName() = if (countryOfOrigin != "JP") mainName() else nameRomaji
}